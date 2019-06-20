package com.maf.news.domain.usecases

import com.maf.news.data.models.Article
import com.maf.news.data.repositories.news.NewsRepository
import io.reactivex.Flowable
import io.reactivex.functions.Function

/**
 * Use case, used to get top headlines for specific country
 * */
class GetTopHeadlines(private val newsRepository: NewsRepository) : Function<Int, Flowable<List<Article>>> {
    override fun apply(page: Int): Flowable<List<Article>> =
        newsRepository
            .getTopHeadlines(page)
            .map { it.articles }
}