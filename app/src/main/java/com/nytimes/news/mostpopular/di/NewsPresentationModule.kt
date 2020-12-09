package com.nytimes.news.mostpopular.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nytimes.news.base.components.ViewModelFactory
import com.nytimes.news.base.di.modules.ViewModelKey
import com.nytimes.news.mostpopular.presentation.viewmodel.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class NewsPresentationModule {
    @Binds
    abstract fun bindsViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindsNewsViewModel(mNewsViewModel: NewsViewModel): ViewModel
}