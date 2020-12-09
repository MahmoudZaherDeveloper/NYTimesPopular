package com.nytimes.news.mostpopular.data.local

import com.nytimes.news.base.caching.LocalDatabaseClient
import com.nytimes.news.base.caching.LocalDatabaseTypeConverter
import com.nytimes.news.base.constants.Period
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class NewsLocalDataSourceImpl @Inject constructor(val localDatabaseTypeConverter: LocalDatabaseTypeConverter) :
    NewsLocalDataSource {

    override suspend fun getNewsArticles(period: Period) = withContext(Dispatchers.IO) {
        LocalDatabaseClient.appDatabase
            .newsArticleDao()?.getAllNews(period.value)?.newsArticleData?.let {
                localDatabaseTypeConverter.getNewsArticles(it)
            } ?: emptyList()

    }


    override suspend fun saveAllNews(newsArticle: NewsArticleEntity) =
        withContext(Dispatchers.IO) {
            LocalDatabaseClient.appDatabase
                .newsArticleDao()?.insertAll(newsArticle)
        }

}