package com.maf.news.data.repositories.news

import com.maf.news.data.api.NewsApi
import com.maf.news.data.models.NewsResponse
import io.reactivex.Flowable

class NewsRepository(private val newsApi: NewsApi) : NewsDataSource {
    override fun getTopHeadlines(page: Int): Flowable<NewsResponse> {
        return newsApi.getTopHeadlines(page)
    }
}