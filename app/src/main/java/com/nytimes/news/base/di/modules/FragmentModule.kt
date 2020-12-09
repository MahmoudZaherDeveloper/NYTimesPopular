package com.nytimes.news.base.di.modules

import com.nytimes.news.mostpopular.presentation.ui.fragments.NewsDetailsFragment
import com.nytimes.news.mostpopular.presentation.ui.fragments.NewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @get:ContributesAndroidInjector
    val newsFragment: NewsFragment?

    @get:ContributesAndroidInjector
    val newsDetailsFragment: NewsDetailsFragment?

}