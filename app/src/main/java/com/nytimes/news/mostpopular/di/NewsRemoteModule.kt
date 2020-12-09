package com.nytimes.news.mostpopular.di


import com.nytimes.news.mostpopular.data.remote.services.NewsService
import com.nytimes.news.mostpopular.data.remote.source.NewsRemoteDataSource
import com.nytimes.news.mostpopular.data.remote.source.NewsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module(includes = [NewsRemoteModule.Binders::class])
class NewsRemoteModule {

    @Module
    interface Binders {
        @Binds
        fun bindsRemoteSource(
            remoteDataSourceImpl: NewsRemoteDataSourceImpl
        ): NewsRemoteDataSource
    }

    @Provides
    fun providesNewsService(retrofit: Retrofit): NewsService =
        retrofit.create(NewsService::class.java)


}