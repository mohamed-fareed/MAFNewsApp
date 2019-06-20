package com.maf.news.domain.engine

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class LazySchedulers(
    private val io: Lazy<Scheduler> = lazy { Schedulers.io() },
    private val main: Lazy<Scheduler> = lazy { AndroidSchedulers.mainThread() }) {

    fun io() = io.value
    fun main() = main.value
}
