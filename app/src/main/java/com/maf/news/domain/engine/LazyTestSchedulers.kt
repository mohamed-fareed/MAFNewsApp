package com.maf.news.domain.engine

import io.reactivex.schedulers.TestScheduler

@Suppress("MemberVisibilityCanBePrivate")
class LazyTestSchedulers(val testScheduler: TestScheduler = TestScheduler()) :
    LazySchedulers(lazy { testScheduler }, lazy { testScheduler }) {

    fun triggerActions() = testScheduler.triggerActions()
}