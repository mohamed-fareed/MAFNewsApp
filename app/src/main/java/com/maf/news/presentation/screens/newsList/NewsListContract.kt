package com.maf.news.presentation.screens.newsList

import com.maf.news.presentation.views.models.ArticleViewModel

interface NewsListContract {
    interface View {
        fun startLoading()

        fun stopLoading()

        fun isLoading(): Boolean

        fun initViews()

        fun addArticles(articleList: List<ArticleViewModel>)

        fun goToSingleArticle()

        fun showFailedToGetFeed()
    }

    interface Presenter {
        fun start()

        fun getArticles(page: Int)

        fun onLoadMoreTriggered(page: Int)

        fun onArticleClicked()

        fun onDestroy()
    }
}