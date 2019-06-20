package com.maf.news.presentation.screens.base

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter : BaseContract.Presenter {
    protected val disposables: CompositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        disposables.dispose()
    }
}