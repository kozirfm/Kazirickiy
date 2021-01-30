package com.example.developerslife.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: Int,
    val description: String,
    val gifURL: String,
)