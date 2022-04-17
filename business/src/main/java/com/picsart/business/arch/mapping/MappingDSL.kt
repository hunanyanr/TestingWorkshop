package com.picsart.business.arch.mapping

/**
 * Created a [Bijection] given the bijective [Mapper] for the current one
 * @receiver The current mapperr
 * @param The bijective [Mapper] from which we want to create the [Bijection]
 */
infix fun <F, P> Mapper<F,P>.biject(bijection:Mapper<P,F>): Bijection<F,P> = object : Bijection<F, P> {
    override fun revert(from: P): F = bijection(from)
    override fun invoke(p1: F): P = this@biject.invoke(p1)
}

/**
 * Creates the identity bijection
 * Where:
 * - x == y therefore f(x) == f(y)
 */
fun <A> Mapper<A, A>.biject(): Bijection<A,A> = this biject this