package br.com.rafaelgabriel.presentation

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class CoroutinePresenter (
    private val presenterContext: CoroutineContext,
    private val baseView: BaseView
): CoroutineScope {
    private val job = Job()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        baseView.showError(throwable)
    }

    override val coroutineContext: CoroutineContext
        get() = presenterContext + job + exceptionHandler

    abstract fun onCreate()

    open fun onDestroy() {
        job.cancel()
    }
}