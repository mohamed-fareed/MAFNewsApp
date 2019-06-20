package com.maf.news.data.api

import com.maf.news.data.models.NewsResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("/v2/top-headlines?country=us&apiKey=7ff9c8931c8743b9bb3faec6af698bd5")
    fun getTopHeadlines(@Query("page") page: Int): Flowable<NewsResponse>
}