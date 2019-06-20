package com.maf.news.data.repositories.news

import com.maf.news.data.api.NewsApi
import com.maf.news.data.api.RetrofitClient
import com.maf.news.data.models.NewsResponse
import io.reactivex.Flowable

/**
 * Implementation for news dataSource, also used to control data source if we have multiple ones like api and databases
 * **/

val defaultNewsRepository = lazy { NewsRepository() }

class NewsRepository(private val newsApi: NewsApi = newsApiInterface()) : NewsDataSource {
    override fun getTopHeadlines(page: Int): Flowable<NewsResponse> {
        return newsApi.getTopHeadlines(page)
    }
}

fun newsApiInterface(): NewsApi = RetrofitClient.retrofit.create(NewsApi::class.java)