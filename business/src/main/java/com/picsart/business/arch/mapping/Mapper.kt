package com.picsart.business.arch.mapping

/**
 * A simple mapper representing a function f(x) = y
 */
fun interface Mapper<A, B>: (A) -> B {
    fun map(a: A): B = invoke(a)
}
