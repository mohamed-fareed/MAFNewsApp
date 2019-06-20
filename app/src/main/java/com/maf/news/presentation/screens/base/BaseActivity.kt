package com.maf.news.presentation.screens.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), BaseContract.View {
    lateinit var presenter: BaseContract.Presenter

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}