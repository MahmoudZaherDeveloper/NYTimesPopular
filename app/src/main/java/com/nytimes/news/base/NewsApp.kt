package com.nytimes.news.base

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.facebook.drawee.backends.pipeline.Fresco
import com.nytimes.news.base.caching.LocalDatabase
import com.nytimes.news.base.caching.LocalDatabaseClient
import com.nytimes.news.base.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class NewsApp : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>


    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)

        Fresco.initialize(this)

        LocalDatabaseClient.appDatabase =
            Room.databaseBuilder(applicationContext, LocalDatabase::class.java, "news.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

}