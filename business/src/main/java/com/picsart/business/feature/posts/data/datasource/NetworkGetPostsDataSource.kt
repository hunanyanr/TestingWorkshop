package com.picsart.business.feature.posts.data.datasource

import arrow.core.Either
import arrow.core.computations.either
import arrow.core.flatMap
import arrow.core.left
import arrow.core.right
import com.picsart.business.arch.GetListDataSource
import com.picsart.business.arch.error.DataNotFound
import com.picsart.business.arch.error.Empty
import com.picsart.business.arch.error.Failure
import com.picsart.business.arch.error.ServerError
import com.picsart.business.feature.posts.data.model.PostDTO
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*

/**
 * Tests: NetworkGetPostsDataSourceTest.kt
 */
internal class NetworkGetPostsDataSource(
    private val endpoint: String,
    private val httpClient: HttpClient
) : GetListDataSource<PostDTO> {
    override suspend fun invoke(): Either<Failure, List<PostDTO>> =
        Either.catch { httpClient.get("$endpoint/posts").body<List<PostDTO>>() }
            .mapLeft {
                when (it) {
                    is ClientRequestException -> DataNotFound
                    else -> ServerError
                }
            }
            .flatMap { if(it.isEmpty()) Empty.left() else it.right() }
}