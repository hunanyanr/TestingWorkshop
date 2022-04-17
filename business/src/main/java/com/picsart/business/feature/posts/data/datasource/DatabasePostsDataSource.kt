package com.picsart.business.feature.posts.data.datasource

import arrow.core.Either
import com.picsart.business.arch.StoreDataSource
import com.picsart.business.arch.error.Failure
import com.picsart.business.feature.posts.data.model.PostDBO

/**
 * Tests: DatabasePostsDataSourceTest.kt
 */
internal class DatabasePostsDataSource : StoreDataSource<PostDBO> {

    private val instance: MutableList<PostDBO> = mutableListOf()

    override suspend fun invoke(): Either<Failure,List<PostDBO>> = TODO()

    override suspend fun invoke(put: List<PostDBO>): Either<Failure, List<PostDBO>> = TODO()
}