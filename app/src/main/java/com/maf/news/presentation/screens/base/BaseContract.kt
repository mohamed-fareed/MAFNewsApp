package com.maf.news.presentation.screens.base

interface BaseContract {
    interface View {
        fun initViews()
    }

    interface Presenter {
        fun onDestroy()
    }
}