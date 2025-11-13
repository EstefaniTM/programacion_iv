package com.ute.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PostDTO(
    val userId: Int = 1,
    val id: Int? = null,
    val title: String,
    val body: String
)