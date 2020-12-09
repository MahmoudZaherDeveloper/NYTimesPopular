package com.nytimes.news.mostpopular.data.remote.source

import com.nytimes.news.base.constants.Period
import com.nytimes.news.mostpopular.data.models.NewsArticle


interface NewsRemoteDataSource {

    suspend fun getNewsArticlesAsync(period: Period): List<NewsArticle>

}