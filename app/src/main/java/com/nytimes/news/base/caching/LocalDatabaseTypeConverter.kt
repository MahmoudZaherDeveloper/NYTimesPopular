package com.nytimes.news.base.caching

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nytimes.news.mostpopular.data.models.NewsArticle
import javax.inject.Inject

class LocalDatabaseTypeConverter @Inject constructor(val gson: Gson) {
    fun getNewsArticles(value: String): List<NewsArticle> {
        val listType =
            object : TypeToken<List<NewsArticle>>() {}.type
        return gson.fromJson(value, listType)
    }

    fun fromNewsArticlews(newsArticles: List<NewsArticle>): String {
        return gson.toJson(newsArticles)
    }
}