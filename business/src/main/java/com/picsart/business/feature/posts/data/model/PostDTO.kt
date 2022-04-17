package com.picsart.business.feature.posts.data.model
import kotlinx.serialization.Serializable

@Serializable
internal data class PostDTO(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String)