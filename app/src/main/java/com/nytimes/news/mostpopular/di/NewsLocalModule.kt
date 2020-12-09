package com.nytimes.news.mostpopular.di


import com.nytimes.news.mostpopular.data.local.NewsLocalDataSource
import com.nytimes.news.mostpopular.data.local.NewsLocalDataSourceImpl
import dagger.Binds
import dagger.Module


@Module(includes = [NewsLocalModule.Binders::class])
class NewsLocalModule {

    @Module
    interface Binders {
        @Binds
        fun bindsLocalSource(
            localDataSourceImpl: NewsLocalDataSourceImpl
        ): NewsLocalDataSource
    }

}