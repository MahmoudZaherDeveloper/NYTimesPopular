package com.nytimes.news.base.di.modules

import androidx.annotation.MainThread
import com.nytimes.news.base.di.qualifiers.CoroutinesIO
import com.nytimes.news.base.di.qualifiers.CoroutinesMainThread
import com.nytimes.news.base.di.qualifiers.IO
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlin.coroutines.CoroutineContext

@Module
class SchedulersModule {

    @Provides
    @IO
    fun bindIoScheduler(): Scheduler {
        return Schedulers.io()
    }

    @Provides
    @MainThread
    fun bindMainThreadScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }


    @CoroutinesIO
    @Provides
    fun providesIoDispatcher(): CoroutineContext {
        return IO
    }

    @CoroutinesMainThread
    @Provides
    fun providesMainThreadDispatcher(): CoroutineContext {
        return Main
    }

}