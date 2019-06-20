package com.maf.news.presentation.screens.newsList

import com.maf.news.data.models.Article
import com.maf.news.domain.engine.LazySchedulers
import com.maf.news.domain.usecases.GetTopHeadlines
import com.maf.news.presentation.screens.base.BasePresenter
import com.maf.news.presentation.views.models.ArticleViewModel
import io.reactivex.disposables.CompositeDisposable

class NewsListPresenter(
    private val view: NewsListContract.View,
    private val lazySchedulers: LazySchedulers = LazySchedulers(),
    private val getTopHeadlinesUseCase: GetTopHeadlines = GetTopHeadlines()
) : BasePresenter(), NewsListContract.Presenter {

    private var articlesList: List<Article> = listOf()

    override fun start() {
        view.initViews()
        getArticles(1)
    }

    override fun onLoadMoreTriggered(page: Int) {
        getArticles(page)
    }

    override fun onArticleClicked(articleId: String) {
        val clickedArticle = articlesList.first { it.publishedAt == articleId } // we are using published at as an ID
        view.goToSingleArticle(clickedArticle)
    }

    override fun getArticles(page: Int) {
        getTopHeadlinesUseCase.apply(page)
            .takeUnless { view.isLoading() }
            ?.also { view.startLoading() }
            ?.subscribeOn(lazySchedulers.io())
            ?.observeOn(lazySchedulers.main())
            ?.subscribe({
                articlesList += it

                view.stopLoading()
                view.addArticles(mapArticlesToViewModels(articlesList))
            }, {
                view.showFailedToGetFeed()
            })
            ?.let(disposables::add)
    }

    private fun mapArticlesToViewModels(articles: List<Article>) =
        articles.map {
            ArticleViewModel(it.publishedAt, it.title, it.description, it.urlToImage, it.publishedAt)
        }
}