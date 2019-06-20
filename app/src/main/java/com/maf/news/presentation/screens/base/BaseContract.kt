package com.maf.news.presentation.screens.base

interface BaseContract {
    interface View {

    }

    interface Presenter {
        fun onDestroy()
    }
}