package com.picsart.business.arch

import arrow.core.Either
import com.picsart.business.arch.error.Failure

/**
 * A simple representation of a data source for getting a list of [P]
 */
interface GetListDataSource<P> {
    suspend operator fun invoke(): Either<Failure, List<P>>
}