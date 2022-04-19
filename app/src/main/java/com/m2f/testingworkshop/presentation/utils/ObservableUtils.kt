package com.m2f.testingworkshop.presentation.utils

import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.picsart.business.arch.error.Failure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

context(ComponentActivity)
fun <T> Flow<State<T>>.render(actionToPerform: RenderingScope<T>.() -> Unit) {
    val renderingScope: RenderingScope<T> = DefaultRenderingScope()
    actionToPerform(renderingScope).also {
        lifecycleScope.launch {
            collect {
                when (it) {
                    is Error -> renderingScope.onError(it.failure)
                    is Loaded -> renderingScope.onLoaded(it.value)
                    Loading -> renderingScope.onLoading()
                    Idle -> renderingScope.onIdle()
                }
            }
        }
    }
}

interface RenderingScope<T> {
    var onEmpty: () -> Unit
    var onError: (Failure) -> Unit
    var onLoaded: (T) -> Unit
    var onLoading: () -> Unit
    var onIdle: () -> Unit
}

private class DefaultRenderingScope<T> : RenderingScope<T> {
    override var onEmpty: () -> Unit = {}
    override var onError: (Failure) -> Unit = {}
    override var onLoaded: (T) -> Unit = {}
    override var onLoading: () -> Unit = {}
    override var onIdle: () -> Unit = {}
}

