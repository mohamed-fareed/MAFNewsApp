package com.maf.news.data.repositories.news

import com.maf.news.data.models.NewsResponse
import io.reactivex.Flowable

interface NewsDataSource {
    fun getTopHeadlines(page: Int): Flowable<NewsResponse>
}