package com.picsart.business.arch

import arrow.core.Either
import com.picsart.business.arch.error.Failure
import com.picsart.business.arch.mapping.Mapper

/**
 * A class that uses a [Mapper] to bypass the type [F] of it's scope allowing to
 * work in the codomain of [P] on [GetListDataSource].
 *
 * As a [GetListDataSource] represents a READ in the CRUD operations it's only a
 * one way path, hence we only need a single mapping to translate the data.
 *
 * Take a look into [BijectedDataSource] if you need to work with a Write and Read operations.
 *
 * @property dataSource The original [GetListDataSource]
 * @property mapper the [Mapper] mapper that translates F to P
 * @author Marc MF
 *
 */
class MappedGetListDataSource<F, P>(
    private val dataSource: GetListDataSource<F>,
    private val mapper: Mapper<F, P>
) : GetListDataSource<P> {
    override suspend fun invoke(): Either<Failure, List<P>> = dataSource().map { it.map(mapper) }
}
