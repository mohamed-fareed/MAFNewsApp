package com.maf.news.presentation.screens.newsList

import android.view.View
import com.airbnb.epoxy.TypedEpoxyController
import com.maf.news.presentation.views.models.ArticleViewModel
import com.maf.news.presentation.views.*

class NewsListController(private val listener: Listener) : TypedEpoxyController<NewsListController.NewsListState>() {

    override fun buildModels(data: NewsListState?) {
        data?.apply {

            articles?.apply {

                // add large article as first item
                largeArticleModel {
                    id(first().id)
                    articleData(first())
                    clickListener(View.OnClickListener { listener.onArticleClicked(first().id) })
                }

                // add other articles
                articles.listIterator(1).forEach { article ->
                    smallArticleModel {
                        id(article.id)
                        articleData(article)
                        clickListener(View.OnClickListener { listener.onArticleClicked(article.id) })
                    }
                }
            }

            if (isLoading)
                epoxyProgressBar {
                    id("Loading")
                }
        }
    }

    data class NewsListState(
        val isLoading: Boolean = false,
        val articles: List<ArticleViewModel>? = null
    )

    interface Listener {
        fun onArticleClicked(id: String)
    }
}