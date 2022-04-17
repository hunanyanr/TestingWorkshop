package com.picsart.business.di

import com.picsart.business.arch.*
import com.picsart.business.arch.mapping.Bijection
import com.picsart.business.arch.mapping.Mapper
import com.picsart.business.di.dependencies.DevelopmentNetworkDependencies
import com.picsart.business.di.dependencies.NetworkDependencies
import com.picsart.business.feature.posts.data.datasource.DatabasePostsDataSource
import com.picsart.business.feature.posts.data.datasource.NetworkGetPostsDataSource
import com.picsart.business.feature.posts.data.mapper.PostDBOToPostBijection
import com.picsart.business.feature.posts.data.mapper.PostDTOToPostMapper
import com.picsart.business.feature.posts.data.model.PostDBO
import com.picsart.business.feature.posts.data.model.PostDTO
import com.picsart.business.feature.posts.data.repository.DefaultGetPostsRepository
import com.picsart.business.feature.posts.model.Post
import com.picsart.business.feature.posts.usecase.DefaultGetPostsUsecase


/**
 * This builds up the provided objects that [Module] exposes
 */
internal object DevelopmentModule : Module {
    private val network: NetworkDependencies by lazy { DevelopmentNetworkDependencies() }

    override val postsUseCase: GetListUseCase<Post> by lazy { DefaultGetPostsUsecase(getPostsRepository) }

    //region private providers
    //mappers
    private val databasePostBijection: Bijection<PostDBO, Post> by lazy { PostDBOToPostBijection }
    private val networkPostMapper: Mapper<PostDTO, Post> by lazy { PostDTOToPostMapper }

    //datasources
    private val networkDataSource: GetListDataSource<Post> by lazy {
        NetworkGetPostsDataSource(network.baseEndpoint(), network.httpClient())
            .map(networkPostMapper)
    }
    private val databaseDataSource: StoreDataSource<Post> by lazy { DatabasePostsDataSource().biject(databasePostBijection) }

    //repositories
    private val getPostsRepository: GetListRepository<Post> by lazy {
        DefaultGetPostsRepository(
            networkDataSource, databaseDataSource
        )
    }
    //endregion

}

