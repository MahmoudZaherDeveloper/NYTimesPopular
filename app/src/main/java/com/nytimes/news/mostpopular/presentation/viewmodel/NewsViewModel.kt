package com.nytimes.news.mostpopular.presentation.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nytimes.news.base.constants.Period
import com.nytimes.news.base.models.APIResponse
import com.nytimes.news.mostpopular.data.models.NewsArticle
import com.nytimes.news.mostpopular.domain.NewsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject


class NewsViewModel @Inject constructor(
    private val mNewsUseCase: NewsUseCase
) : ViewModel() {

    val newsResult = MediatorLiveData<APIResponse<List<NewsArticle>>>()
    var newsArticle: NewsArticle? = null

    fun getNewsArticlesAysnc(period: Period = Period.DAILY, forceNetwork: Boolean = false) {
        viewModelScope.launch {
            newsResult.addSource(mNewsUseCase.getNewsArticlesAsync(period, forceNetwork)) {
                newsResult.value = it
            }
        }
    }

}