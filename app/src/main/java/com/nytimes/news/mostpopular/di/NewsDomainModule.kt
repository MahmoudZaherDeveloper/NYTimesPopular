package com.nytimes.news.mostpopular.di

import com.nytimes.news.mostpopular.data.repository.NewsRepository
import com.nytimes.news.mostpopular.data.repository.NewsRepositoryImpl
import com.nytimes.news.mostpopular.domain.NewsUseCase
import com.nytimes.news.mostpopular.domain.NewsUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class NewsDomainModule {

    @Binds
    abstract fun bindsRepository(
        repoImpl: NewsRepositoryImpl
    ): NewsRepository


    @Binds
    abstract fun bindsNewsUseCase(
        mNewsUseCase: NewsUseCaseImpl
    ): NewsUseCase


}