package com.nytimes.news.mostpopular.data.remote.services

import com.nytimes.news.base.constants.APIKey
import com.nytimes.news.mostpopular.data.models.NewsArticleResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsService {

    @GET("{period}.json?api-key=${APIKey}")
    fun getNewsArticlesAsync(@Path("period") period: String): Deferred<Response<NewsArticleResponse>>

}