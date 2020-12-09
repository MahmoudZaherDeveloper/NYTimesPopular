package com.nytimes.news.mostpopular.data.remote.source

import com.nytimes.news.base.constants.Period
import com.nytimes.news.base.di.qualifiers.CoroutinesIO
import com.nytimes.news.mostpopular.data.remote.services.NewsService
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class NewsRemoteDataSourceImpl @Inject constructor(
    private val service: NewsService,
    @CoroutinesIO private val context: CoroutineContext
) : NewsRemoteDataSource {
    override suspend fun getNewsArticlesAsync(period: Period) = withContext(context) {
        val response = service.getNewsArticlesAsync(period.value).await()

        if (response.isSuccessful)
            response.body()?.results ?: throw Exception("no news articles")
        else
            throw Exception("couldn't fetch news articles")
    }
}
