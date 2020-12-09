package com.nytimes.news.base.di.modules

import com.nytimes.news.mostpopular.presentation.ui.activities.NewsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @get:ContributesAndroidInjector
    val newsActivity: NewsActivity?

}