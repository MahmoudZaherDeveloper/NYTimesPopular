package com.nytimes.news.mostpopular.domain

import com.nytimes.news.base.constants.Period
import com.nytimes.news.mostpopular.data.repository.NewsRepository
import javax.inject.Inject

class NewsUseCaseImpl @Inject constructor(private val mNewsRepository: NewsRepository) :
    NewsUseCase {

    override suspend fun getNewsArticlesAsync(
        period: Period,
        forceNetwork: Boolean
    ) = mNewsRepository.getNewsArticlesAsync(period, forceNetwork)

}
