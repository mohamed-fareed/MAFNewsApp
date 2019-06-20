package com.maf.news.data.repositories.news

import com.maf.news.data.models.NewsResponse
import io.reactivex.Flowable

/**
 * This interface is to hold all operations on news data source
 * */
interface NewsDataSource {

    /**
     * @param page: used to get top headlines news paginated
     * */
    fun getTopHeadlines(page: Int): Flowable<NewsResponse>
}