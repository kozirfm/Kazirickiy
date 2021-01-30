package com.example.developerslife.model.repository

import com.example.developerslife.data.entity.Root

interface Repository {
    suspend fun getPosts(section: String, page: Int): Root
}