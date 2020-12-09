package com.nytimes.news.mostpopular.presentation.ui.activities

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import com.nytimes.news.R
import com.nytimes.news.base.presentation.BaseActivity
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
    }

    fun updateTitle(@StringRes title: Int) {
        tvTitle.setText(title)
    }

    fun setFilterAction(onFilterClickListener: () -> Unit) {
        ivFilter.setOnClickListener {
            onFilterClickListener.invoke()
        }
    }

    fun toggleFilterAction(show: Boolean) {
        ivFilter.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }
}
