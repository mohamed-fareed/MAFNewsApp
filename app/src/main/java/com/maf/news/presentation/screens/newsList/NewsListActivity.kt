package com.maf.news.presentation.screens.newsList

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.maf.news.R
import com.maf.news.data.models.Article
import com.maf.news.presentation.screens.base.BaseActivity
import com.maf.news.presentation.screens.details.ArticleDetailsActivity
import com.maf.news.presentation.screens.details.ArticleDetailsActivity.Companion.EXTRAS_ARTICLE
import com.maf.news.presentation.utils.EndlessRecyclerViewScrollListener
import com.maf.news.presentation.views.models.ArticleViewModel
import kotlinx.android.synthetic.main.activity_news_list.*

class NewsListActivity : BaseActivity(), NewsListContract.View,
    NewsListController.Listener {

    private val controller: NewsListController by lazy {
        NewsListController(this)
            .also {
                it.setData(NewsListController.NewsListState())
            }
    }

    private val linearLayoutManager = LinearLayoutManager(this)

    private val endlessScrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
        override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
            (presenter as NewsListPresenter).onLoadMoreTriggered(page)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        presenter = NewsListPresenter(this)
        (presenter as NewsListPresenter).start()
    }

    override fun startLoading() {
        controller.setData(
            controller.currentData?.copy(
                isLoading = true
            )
        )
    }

    override fun stopLoading() {
        controller.setData(
            controller.currentData?.copy(
                isLoading = false
            )
        )
    }

    override fun isLoading(): Boolean {
        return controller.currentData?.isLoading ?: false
    }

    override fun initViews() {
        with(rv_news) {
            layoutManager = linearLayoutManager
            adapter = controller.adapter
            addOnScrollListener(endlessScrollListener)
        }
    }

    override fun addArticles(articleList: List<ArticleViewModel>) {
        controller.setData(
            controller.currentData?.copy(
                articles = articleList
            )
        )
    }

    override fun goToSingleArticle(article: Article) {
        val intent = Intent(this, ArticleDetailsActivity::class.java)
            .putExtra(Gson().toJson(article), EXTRAS_ARTICLE)
        startActivity(intent)
    }

    override fun onArticleClicked(id: String) {
        (presenter as NewsListPresenter).onArticleClicked()
    }

    override fun showFailedToGetFeed() =
        Toast.makeText(this, getString(R.string.error_load_feed), Toast.LENGTH_LONG).show()
}
