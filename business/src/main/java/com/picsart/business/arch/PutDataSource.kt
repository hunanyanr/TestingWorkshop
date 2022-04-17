package com.picsart.business.arch

import arrow.core.Either
import com.picsart.business.arch.error.Failure

/**
 * A simple representation of a use case for saving a list of [P]
 */
interface PutListDataSource<P> {
    suspend operator fun invoke(put: List<P>): Either<Failure, List<P>>
}
