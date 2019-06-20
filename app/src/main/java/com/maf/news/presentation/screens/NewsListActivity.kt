package com.maf.news.presentation.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maf.news.R

class NewsListActivity : AppCompatActivity(), NewsListContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
    }
}
