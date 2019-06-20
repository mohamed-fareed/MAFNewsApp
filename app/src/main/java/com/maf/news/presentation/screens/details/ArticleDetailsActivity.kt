package com.maf.news.presentation.screens.details

import android.os.Bundle
import com.google.gson.Gson
import com.maf.news.R
import com.maf.news.data.models.Article
import com.maf.news.presentation.loadUrl
import com.maf.news.presentation.screens.base.BaseActivity
import kotlinx.android.synthetic.main.activity_article_details.*

class ArticleDetailsActivity : BaseActivity(), ArticleDetailsContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)

        val articleString = intent.getStringExtra(EXTRAS_ARTICLE)
        val article = Gson().fromJson(articleString, Article::class.java)

        presenter = ArticleDetailsPresenter(this)
        (presenter as ArticleDetailsPresenter).start(article)
    }

    override fun initViews(article: Article) {
        img_article.loadUrl(article.urlToImage)
        tv_article_title.text = article.title
        tv_article_date.text = article.publishedAt
        tv_article_content.text = article.content
    }

    companion object {
        const val EXTRAS_ARTICLE = "article"
    }
}
