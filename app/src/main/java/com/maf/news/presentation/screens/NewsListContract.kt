package com.maf.news.presentation.screens

import com.maf.news.presentation.views.models.ArticleViewModel

interface NewsListContract {
    interface View {
        fun startLoading()

        fun stopLoading()

        fun isLoading(): Boolean

        fun initViews()

        fun addArticles(articleList: List<ArticleViewModel>)

        fun goToSingleArticle()
    }

    interface Presenter {
        fun start()

        fun onLoadMoreTriggered(page: Int)

        fun onArticleClicked()

        fun onDestroy()
    }
}