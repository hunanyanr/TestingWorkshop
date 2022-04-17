package com.picsart.business.arch

import arrow.core.Either
import com.picsart.business.arch.error.Failure
import com.picsart.business.arch.mapping.Bijection

/**
 * A class that uses a [Bijection] to bypass the type [F] of it's scope allowing to
 * work in the codomain of [P] on [StoreDataSource]
 * @property storeDataSource The original [StoreDataSource]
 * @property bijection the bijective mapper that translates F to P and vice versa.
 * @author Marc MF
 *
 * As [StoreDataSource] represents a Read and a Write in the CRUD operations we need a two way
 * mapping.
 *
 * @sample ```
 * Assume that we have a DatabaseDataSource that implements StoreDataSource, is in the codomain
 * of DBObject and we need to build a structure of datasources and repositorries in the codomain of Object.
 *
 * If we have a bijection that knows how to translate DBObjects and Objects we can convert the
 * DatabaseDataSource<DBObject> into StoreDataSource<Object> thanks to our friend polymorphism
 * ```
 */
class BijectedDataSource<F, P>(
    private val storeDataSource: StoreDataSource<F>,
    private val bijection: Bijection<F, P>
) : StoreDataSource<P> {

    override suspend fun invoke(): Either<Failure, List<P>> =
        storeDataSource().map { it.map(bijection::invoke) }

    override suspend fun invoke(put: List<P>): Either<Failure,List<P>> =
        storeDataSource(put.map(bijection::revert)).map { it.map(bijection::invoke) }
}