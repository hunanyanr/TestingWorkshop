package com.m2f.testingworkshop.presentation.utils

import androidx.lifecycle.LiveData
import androidx.activity.ComponentActivity
import com.picsart.business.arch.error.Failure

context(ComponentActivity)
fun <T> LiveData<State<T>>.render(actionToPerform: RenderingScope<T>.() -> Unit) {
    val renderingScope: RenderingScope<T> = DefaultRenderingScope()
    observe(this@ComponentActivity) {
        when(it) {
            is Error ->  renderingScope.onError(it.failure)
            is Loaded ->  renderingScope.onLoaded(it.value)
            Loading ->  renderingScope.onLoading()
            Idle -> renderingScope.onIdle()
        }
    }
    actionToPerform(renderingScope)
}

interface RenderingScope<T> {
    var onEmpty: () -> Unit
    var onError: (Failure) -> Unit
    var onLoaded: (T) -> Unit
    var onLoading: () -> Unit
    var onIdle: () -> Unit
}

private class DefaultRenderingScope<T>: RenderingScope<T> {
    override var onEmpty: () -> Unit = {}
    override var onError: (Failure) -> Unit = {}
    override var onLoaded: (T) -> Unit = {}
    override var onLoading: () -> Unit = {}
    override var onIdle: () -> Unit = {}
}

