package com.maf.news.presentation.screens

interface NewsListContract {
    interface View {
        fun initViews()

        fun goToSingleArticle()
    }

    interface Presenter {
        fun start()

        fun onArticleClicked()
    }
}