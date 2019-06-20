package com.maf.news.presentation.screens

import com.maf.news.data.models.Article
import com.maf.news.domain.usecases.GetTopHeadlines
import com.maf.news.presentation.views.models.ArticleViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewsListPresenter(
    private val view: NewsListContract.View,
    private val getTopHeadlinesUseCase: GetTopHeadlines = GetTopHeadlines()
) : NewsListContract.Presenter {

    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun start() {
        view.initViews()

        getArticles()
    }

    private fun getArticles() {
        getTopHeadlinesUseCase.apply(1)
            .also { view.startLoading() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.stopLoading()
                view.addArticles(mapArticlesToViewModels(it))
            }, {})
            .let(disposables::add)
    }

    override fun onArticleClicked() {
        view.goToSingleArticle()
    }

    override fun onDestroy() {
        disposables.dispose()
    }

    private fun mapArticlesToViewModels(articles: List<Article>) =
        articles.map {
            ArticleViewModel(it.title, it.title, it.description, it.urlToImage, it.publishedAt)
        }
}