package com.maf.news.presentation.screens.details

import com.maf.news.data.models.Article
import com.maf.news.presentation.screens.base.BasePresenter

class ArticleDetailsPresenter(private val view: ArticleDetailsContract.View) : BasePresenter(),
    ArticleDetailsContract.Presenter {

    override fun start(article: Article) {
        view.initViews(article)
    }
}