package com.nytimes.news.mostpopular

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nytimes.news.MainCoroutineRule
import com.nytimes.news.base.constants.Period
import com.nytimes.news.mostpopular.data.models.Media
import com.nytimes.news.mostpopular.data.models.MediaMetaData
import com.nytimes.news.mostpopular.data.models.NewsArticle
import com.nytimes.news.mostpopular.data.models.NewsArticleResponse
import com.nytimes.news.mostpopular.data.remote.services.NewsService
import com.nytimes.news.mostpopular.data.remote.source.NewsRemoteDataSource
import com.nytimes.news.mostpopular.data.remote.source.NewsRemoteDataSourceImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response


class NewsRemoteDataSourceTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var remoteDataSource: NewsRemoteDataSource

    lateinit var service: NewsService

    val response = NewsArticleResponse(
        "success", "test", 1,
        listOf<NewsArticle>(
            NewsArticle(
                1, "test news", "test abstract", "04-12-2020", listOf(
                    Media(
                        "image",
                        "imageSub",
                        "test",
                        "test copyright",
                        1,
                        listOf(MediaMetaData("https://test.com", "jpg", 200, 200))
                    )
                )
            )
        )
    )

    @Before
    fun init() {
        service = mock {
            onBlocking {
                getNewsArticlesAsync("1")
            } doReturn GlobalScope.async {
                Response.success(response)
            }
        }
        remoteDataSource = NewsRemoteDataSourceImpl(service, mainCoroutineRule.coroutineContext)
    }

    @Test
    fun testGetNewsArticlesSuccessResponse() = runBlocking {
        val result = remoteDataSource.getNewsArticlesAsync(Period.DAILY)
        assert(result == response.results)
    }
}
