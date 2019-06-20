package com.maf.news.domain.usecases

import com.maf.news.data.models.Article
import com.maf.news.data.models.NewsResponse
import com.maf.news.data.models.Source
import com.maf.news.data.repositories.news.NewsRepository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Flowable
import org.junit.Test

class GetTopHeadlinesTest {

    @Test
    fun requestTopHeadlinesThenReturnArticlesList() {
        val page = 1

        val newsRepository = mock<NewsRepository> {
            on { getTopHeadlines(page) } doReturn Flowable.fromArray(createArticlesArray())
        }

        with(GetTopHeadlines(lazy { newsRepository })) {
            apply(page).blockingIterable()
        }

        verify(newsRepository).getTopHeadlines(page)
    }

    private fun createArticlesArray(): NewsResponse {
        val articleList = listOf<Article>()
        repeat(10) {
            articleList.plus(
                Article(
                    Source("", ""),
                    "", "", "", "", "", "", ""
                )
            )
        }

        return NewsResponse("", 10, articleList)
    }

}
