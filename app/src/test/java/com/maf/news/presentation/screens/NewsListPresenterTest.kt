package com.maf.news.presentation.screens

import com.maf.news.data.models.Article
import com.maf.news.data.models.Source
import com.maf.news.domain.engine.LazyTestSchedulers
import com.maf.news.domain.usecases.GetTopHeadlines
import com.maf.news.presentation.screens.newsList.NewsListContract
import com.maf.news.presentation.screens.newsList.NewsListPresenter
import com.maf.news.presentation.views.models.ArticleViewModel
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Flowable
import org.junit.Test

class NewsListPresenterTest {

    @Test
    fun getArticlesWithSuccessfulResponseThenAddArticlesToList() {
        val page = 1
        val view = mock<NewsListContract.View>()
        val schedulers = LazyTestSchedulers()
        val getTopHeadlinesUseCase = mock<GetTopHeadlines> {
            on { apply(page) } doReturn Flowable.fromArray(createArticlesArray())
        }

        with(NewsListPresenter(view, schedulers, getTopHeadlinesUseCase)) {
            getArticles(page)
            schedulers.triggerActions()
        }

        verify(view).addArticles(createArticlesViewModels())
    }

    @Test
    fun getArticlesWithFailedResponseThenShowErrorMessage() {
        val page = 1
        val view = mock<NewsListContract.View>()
        val schedulers = LazyTestSchedulers()
        val getTopHeadlinesUseCase = mock<GetTopHeadlines> {
            on { apply(page) } doReturn Flowable.error(Exception("get news error"))
        }

        with(NewsListPresenter(view, schedulers, getTopHeadlinesUseCase)) {
            getArticles(page)
            schedulers.triggerActions()
        }

        verify(view).showFailedToGetFeed()
    }

    private fun createArticlesArray(): List<Article> {
        val articleList = listOf<Article>()
        repeat(10) {
            articleList.plus(
                Article(
                    Source("", ""),
                    "", "", "", "", "", "", ""
                )
            )
        }

        return articleList
    }

    fun createArticlesViewModels() =
        createArticlesArray().map {
            ArticleViewModel(it.publishedAt, it.title, it.description, it.urlToImage, it.publishedAt)
        }
}