package com.example.developerslife.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Root(
    val result: List<Post>,
    val totalCount: Int,
)