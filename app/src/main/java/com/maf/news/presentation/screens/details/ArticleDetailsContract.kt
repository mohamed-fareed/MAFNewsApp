package com.maf.news.presentation.screens.details

import com.maf.news.data.models.Article
import com.maf.news.presentation.screens.base.BaseContract

interface ArticleDetailsContract {
    interface View : BaseContract.View {
        fun initViews(article: Article)
    }

    interface Presenter : BaseContract.Presenter {
        fun start(article: Article)
    }
}