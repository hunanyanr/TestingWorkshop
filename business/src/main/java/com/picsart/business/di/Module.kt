package com.picsart.business.di

import com.picsart.business.arch.GetListUseCase
import com.picsart.business.feature.posts.model.Post

interface Module {
    val postsUseCase: GetListUseCase<Post>
}