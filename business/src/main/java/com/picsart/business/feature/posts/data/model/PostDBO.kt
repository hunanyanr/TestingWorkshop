package com.picsart.business.feature.posts.data.model

internal data class PostDBO(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String)