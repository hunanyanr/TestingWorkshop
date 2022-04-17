package com.picsart.business.feature.posts.data.mapper

import com.picsart.business.arch.mapping.Mapper
import com.picsart.business.feature.posts.data.model.PostDTO
import com.picsart.business.feature.posts.model.Post

/**
 * Tests: PostDTOToPostMapperTest.kt
 */
internal object PostDTOToPostMapper: Mapper<PostDTO, Post> {
    override fun invoke(p1: PostDTO): Post = with(p1) {
        Post(id, userId, title, body)
    }
}