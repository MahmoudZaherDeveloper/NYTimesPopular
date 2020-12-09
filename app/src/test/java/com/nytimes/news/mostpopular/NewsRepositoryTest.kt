package com.nytimes.news.mostpopular

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.nytimes.news.LiveDataTestingUtils
import com.nytimes.news.MainCoroutineRule
import com.nytimes.news.base.caching.LocalDatabaseTypeConverter
import com.nytimes.news.base.constants.Period
import com.nytimes.news.base.models.Status
import com.nytimes.news.mostpopular.data.local.NewsLocalDataSource
import com.nytimes.news.mostpopular.data.models.Media
import com.nytimes.news.mostpopular.data.models.MediaMetaData
import com.nytimes.news.mostpopular.data.models.NewsArticle
import com.nytimes.news.mostpopular.data.models.NewsArticleResponse
import com.nytimes.news.mostpopular.data.remote.source.NewsRemoteDataSource
import com.nytimes.news.mostpopular.data.repository.NewsRepository
import com.nytimes.news.mostpopular.data.repository.NewsRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class NewsRepositoryTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    lateinit var repository: NewsRepository

    @Mock
    lateinit var remoteDataSource: NewsRemoteDataSource

    @Mock
    lateinit var localDataSource: NewsLocalDataSource


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
        MockitoAnnotations.initMocks(this)
        repository =
            NewsRepositoryImpl(
                remoteDataSource,
                localDataSource,
                LocalDatabaseTypeConverter(Gson())
            )
    }

    @Test
    fun testGetNewsArticlesSuccessResponse() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(remoteDataSource.getNewsArticlesAsync(Period.DAILY))
            .thenReturn(response.results)
        val result = repository.getNewsArticlesAsync(Period.DAILY, true)
        assert(LiveDataTestingUtils.getValue(result).status == Status.LOADING)
        assert(LiveDataTestingUtils.getValue(result).status == Status.SUCCESS)
        assert(LiveDataTestingUtils.getValue(result).data == response.results)
    }
}
