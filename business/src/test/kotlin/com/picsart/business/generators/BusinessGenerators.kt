package com.picsart.business.generators

import com.picsart.business.feature.posts.data.model.PostDBO
import com.picsart.business.feature.posts.data.model.PostDTO
import com.picsart.business.feature.posts.model.Post
import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.arbitrary.string

internal fun Arb.Companion.dboPost(): Arb<PostDBO> = arbitrary {
    val title = Arb.string().bind()
    val body = Arb.string().bind()
    val id = Arb.positiveInt().bind()
    val userId = Arb.positiveInt().bind()
    PostDBO(id, userId, title, body)
}

internal fun Arb.Companion.dtoPost(): Arb<PostDTO> = arbitrary {
    val title = Arb.string().bind()
    val body = Arb.string().bind()
    val id = Arb.positiveInt().bind()
    val userId = Arb.positiveInt().bind()
    PostDTO(id, userId, title, body)
}

internal fun Arb.Companion.post(): Arb<Post> = arbitrary {
    val title = Arb.string().bind()
    val body = Arb.string().bind()
    val id = Arb.positiveInt().bind()
    val userId = Arb.positiveInt().bind()
    Post(id, userId, title, body)
}