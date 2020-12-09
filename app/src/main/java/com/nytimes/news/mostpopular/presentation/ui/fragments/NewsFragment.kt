package com.nytimes.news.mostpopular.presentation.ui.fragments


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.nytimes.news.R
import com.nytimes.news.base.components.ViewModelFactory
import com.nytimes.news.base.constants.Period
import com.nytimes.news.base.models.Status
import com.nytimes.news.base.presentation.BaseFragment
import com.nytimes.news.mostpopular.presentation.ui.activities.NewsActivity
import com.nytimes.news.mostpopular.presentation.viewmodel.NewsViewModel
import com.ouday.test.customer.presentation.ui.adapter.NewsArticlesRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject


class NewsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mNewsViewModel: NewsViewModel

    @Inject
    lateinit var newsArticlesRecyclerViewAdapter: NewsArticlesRecyclerViewAdapter

    private var period: Period = Period.DAILY
    private val periods = arrayOf("Daily", "Weekly", "Monthly")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            try {
                mNewsViewModel =
                    ViewModelProvider(
                        requireActivity(),
                        viewModelFactory
                    ).get(NewsViewModel::class.java)

                mNewsViewModel.newsResult.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                    when (it.status) {
                        Status.LOADING -> {
                            swipeRefresh.isRefreshing = true
                        }
                        Status.ERROR -> {
                            swipeRefresh.isRefreshing = false
                            Toast.makeText(
                                requireContext(),
                                it.exception?.message ?: "",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        Status.SUCCESS -> {
                            swipeRefresh.isRefreshing = false
                            newsArticlesRecyclerViewAdapter.setData(it.data ?: emptyList())
                        }
                    }
                })

                if (mNewsViewModel.newsResult.value == null) {
                    getNewsArticles(period)
                }

                init()

            } finally {
                //empty
            }
        }
    }

    private fun init() {
        setupHeader()
        initViews()
        implementListeners()
    }

    private fun setupHeader() {
        (activity as NewsActivity).updateTitle(R.string.news_title)
        (activity as NewsActivity).toggleFilterAction(true)
        (activity as NewsActivity).setFilterAction {
            showPeriodPicker()
        }
    }

    private fun initViews() {
        rvNews.adapter = newsArticlesRecyclerViewAdapter
    }

    private fun implementListeners() {
        swipeRefresh.setOnRefreshListener {
            getNewsArticles(period, true)
        }
        newsArticlesRecyclerViewAdapter.setOnNewsArticleClickListener {
            view?.let { v ->
                mNewsViewModel.newsArticle = it
                Navigation.findNavController(v).navigate(R.id.action_news_details)
            }
        }
    }

    private fun showPeriodPicker() {
        AlertDialog.Builder(requireContext()).setItems(
            periods
        ) { dialog, itemId ->
            getNewsArticles(Period.getPeriod(itemId))
        }.show()
    }

    private fun getNewsArticles(period: Period, forceNetwork: Boolean = false) {
        this.period = period
        mNewsViewModel.getNewsArticlesAysnc(period, forceNetwork)
    }

}
