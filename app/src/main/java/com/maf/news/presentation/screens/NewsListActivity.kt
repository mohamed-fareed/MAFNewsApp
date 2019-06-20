package com.maf.news.presentation.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.maf.news.R
import kotlinx.android.synthetic.main.activity_news_list.*

class NewsListActivity : AppCompatActivity(), NewsListContract.View, NewsListController.Listener {

    private lateinit var presenter: NewsListPresenter

    private val controller: NewsListController by lazy {
        NewsListController(this)
            .also {
                it.setData(NewsListController.NewsListState())
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        presenter = NewsListPresenter(this)
        presenter.start()
    }

    override fun initViews() {
        with(rv_news) {
            layoutManager = LinearLayoutManager(this.context)
            adapter = controller.adapter
        }
    }

    override fun goToSingleArticle() {

    }

    override fun onArticleClicked(id: String) {
        presenter.onArticleClicked()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
