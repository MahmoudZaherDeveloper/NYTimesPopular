package com.nytimes.news.mostpopular.data.repository

import androidx.lifecycle.LiveData
import com.nytimes.news.base.constants.Period
import com.nytimes.news.base.models.APIResponse
import com.nytimes.news.mostpopular.data.models.NewsArticle


interface NewsRepository {

    suspend fun getNewsArticlesAsync(
        period: Period,
        forceNetwork: Boolean
    ): LiveData<APIResponse<List<NewsArticle>>>

}