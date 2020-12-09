package com.ouday.test.customer.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nytimes.news.R
import com.nytimes.news.mostpopular.data.models.Media
import com.nytimes.news.mostpopular.data.models.NewsArticle
import kotlinx.android.synthetic.main.news_item.view.*
import javax.inject.Inject

class NewsArticlesRecyclerViewAdapter @Inject constructor() :
    RecyclerView.Adapter<NewsArticlesRecyclerViewAdapter.ViewHolder>() {

    private val news: ArrayList<NewsArticle> = ArrayList()

    private var onNewsArticleClickListener: ((newsArticle: NewsArticle) -> Unit)? = null

    fun setOnNewsArticleClickListener(onNewsArticleClickListener: ((newsArticle: NewsArticle) -> Unit)) {
        this.onNewsArticleClickListener = onNewsArticleClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.news_item,
                parent,
                false
            )
        )
    }

    fun setData(news: List<NewsArticle>) {
        this.news.clear()
        this.news.addAll(news)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(news[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(newsArticle: NewsArticle) {

            itemView.tvNewsTitle.text = newsArticle.title
            itemView.tvNewsDate.text = newsArticle.publishedDate
            newsArticle.media.firstOrNull()?.getMetaData(Media.MediaSize.THUMB)?.let {
                itemView.ivImage.setImageURI(it.url)
            }

            itemView.setOnClickListener {
                onNewsArticleClickListener?.invoke(newsArticle)
            }
        }

    }

}