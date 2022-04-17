package com.m2f.testingworkshop.presentation.utils

import com.picsart.business.arch.error.Failure

sealed class State<out V>

object Idle: State<Nothing>()
object Loading: State<Nothing>()
data class Loaded<V>(val value: V): State<V>()
data class Error(val failure: Failure): State<Nothing>()