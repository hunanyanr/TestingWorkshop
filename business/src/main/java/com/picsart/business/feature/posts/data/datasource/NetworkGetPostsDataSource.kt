package com.picsart.business.feature.posts.data.datasource

import arrow.core.Either
import com.picsart.business.arch.GetListDataSource
import com.picsart.business.arch.error.Failure
import com.picsart.business.feature.posts.data.model.PostDTO
import io.ktor.client.*

/**
 * Tests: NetworkGetPostsDataSourceTest.kt
 */
internal class NetworkGetPostsDataSource(
    private val endpoint: String,
    private val httpClient: HttpClient
) : GetListDataSource<PostDTO> {
    override suspend fun invoke(): Either<Failure, List<PostDTO>> = TODO()
}