package com.maf.news.domain.usecases

import com.maf.news.data.models.Article
import com.maf.news.data.repositories.news.NewsRepository
import com.maf.news.data.repositories.news.defaultNewsRepository
import io.reactivex.Flowable
import io.reactivex.functions.Function

/**
 * Use case, used to get top headlines for specific country
 * */
class GetTopHeadlines(private val newsRepository: Lazy<NewsRepository> = defaultNewsRepository) :
    Function<Int, Flowable<List<Article>>> {
    override fun apply(page: Int): Flowable<List<Article>> =
        newsRepository
            .value
            .getTopHeadlines(page)
            .map { it.articles }
}