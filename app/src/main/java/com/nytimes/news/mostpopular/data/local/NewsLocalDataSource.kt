package com.nytimes.news.mostpopular.data.local

import com.nytimes.news.base.constants.Period
import com.nytimes.news.mostpopular.data.models.NewsArticle


interface NewsLocalDataSource {

    suspend fun saveAllNews(newsArticle: NewsArticleEntity): Unit?
    suspend fun getNewsArticles(period: Period): List<NewsArticle>
}