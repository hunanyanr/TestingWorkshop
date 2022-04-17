package com.picsart.business.feature.posts.data.mapper

import com.picsart.business.arch.mapping.Bijection
import com.picsart.business.feature.posts.data.model.PostDBO
import com.picsart.business.feature.posts.model.Post

/**
 * Tests: PostDBOToPostBijection.kt
 */
internal object PostDBOToPostBijection : Bijection<PostDBO, Post>{

    override fun invoke(from: PostDBO): Post =
        with(from) { Post(id, userId, title, body) }

    override fun revert(from: Post): PostDBO =
        with(from){ PostDBO(id, userId, title, body) }
}
