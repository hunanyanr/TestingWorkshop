package com.picsart.business.feature.posts.data.repository

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.handleErrorWith
import com.picsart.business.arch.GetListDataSource
import com.picsart.business.arch.GetListRepository
import com.picsart.business.arch.StoreDataSource
import com.picsart.business.arch.error.Failure
import com.picsart.business.feature.posts.model.Post

/**
 * Tests: DefaultGetPostsRepositoryTest.kt
 */
internal class DefaultGetPostsRepository(
    private val networkDataSource: GetListDataSource<Post>,
    private val databaseDataSource: StoreDataSource<Post>
) : GetListRepository<Post> {

    override suspend fun invoke(): Either<Failure, List<Post>> = networkDataSource()
            .flatMap { databaseDataSource(it) }
            .handleErrorWith { databaseDataSource() }
}