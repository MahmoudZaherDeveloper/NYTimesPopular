package com.nytimes.news.mostpopular.domain

import androidx.lifecycle.LiveData
import com.nytimes.news.base.constants.Period
import com.nytimes.news.base.models.APIResponse
import com.nytimes.news.mostpopular.data.models.NewsArticle

interface NewsUseCase {

    suspend fun getNewsArticlesAsync(
        period: Period,
        forceNetwork: Boolean
    ): LiveData<APIResponse<List<NewsArticle>>>
}