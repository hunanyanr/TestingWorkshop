package com.picsart.business.arch.mapping

/**
 * We can define a bijection in this scope as a function f(x) = y and g(y) = x
 * hence f(g(y)) = x.
 * This means that:
 * - If we have a [Mapper<X,Y>] that can be represented as f(x) = y
 * - Then it's bijection is [Mapper<Y,X>] that can be represented as g(y) = x
 *
 * A Bijection interface, then, represents the f(g(y)) = x exposing a [revert] function
 *
 * Take a look into [BijectionLaws] to see how this reciprocity is tested
 */
interface Bijection<F, P>: Mapper<F, P> {
    fun revert(from: P): F
}
