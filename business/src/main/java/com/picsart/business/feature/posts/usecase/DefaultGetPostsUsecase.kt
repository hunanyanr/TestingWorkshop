package com.picsart.business.feature.posts.usecase

import arrow.core.Either
import com.picsart.business.arch.GetListRepository
import com.picsart.business.arch.GetListUseCase
import com.picsart.business.arch.error.Failure
import com.picsart.business.feature.posts.model.Post

/**
 * Tests: DefaultGetPostsUsecaseTest.kt
 */
internal class DefaultGetPostsUsecase(
    private val getPostsRepository: GetListRepository<Post>
) : GetListUseCase<Post> {
    override suspend fun invoke(): Either<Failure, List<Post>> = getPostsRepository()
        .map { it.filter { it.title.isNotBlank() } }
}