package com.nytimes.news.base.di.component

import android.app.Application
import com.nytimes.news.base.NewsApp
import com.nytimes.news.base.di.modules.*
import com.nytimes.news.mostpopular.di.NewsDomainModule
import com.nytimes.news.mostpopular.di.NewsLocalModule
import com.nytimes.news.mostpopular.di.NewsPresentationModule
import com.nytimes.news.mostpopular.di.NewsRemoteModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        NetworkModule::class, ContextModule::class,
        ActivityModule::class,
        FragmentModule::class,
        SchedulersModule::class,
        NewsDomainModule::class,
        NewsPresentationModule::class,
        NewsRemoteModule::class,
        NewsLocalModule::class]
)
interface AppComponent {

    fun inject(app: NewsApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}