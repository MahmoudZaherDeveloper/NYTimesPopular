package com.nytimes.news.base.di.modules

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.nytimes.news.BuildConfig
import com.nytimes.news.base.constants.EndpointUrl
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideCompositeDisposable(): CompositeDisposable? {
        return CompositeDisposable()
    }

    @Provides
    fun provideNetworkIntercepter(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val builder = chain.request().newBuilder()
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
            val request = builder.build()
            chain.proceed(request)
        }
    }

    fun getOkHttpClient(interceptor: Interceptor?): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val logginIntercepter = HttpLoggingInterceptor()
        logginIntercepter.setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
        return builder.readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor!!)
            .addInterceptor(logginIntercepter)
            .build()
    }

    @Provides
    @Singleton
    fun getGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }


    @Provides
    fun getRetrofit(
        gson: Gson,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .baseUrl(EndpointUrl)
            .client(getOkHttpClient(provideNetworkIntercepter()))
            .build()
    }
}