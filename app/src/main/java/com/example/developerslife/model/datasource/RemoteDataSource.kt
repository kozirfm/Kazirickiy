package com.example.developerslife.model.datasource

import com.example.developerslife.data.entity.Root

interface RemoteDataSource {

    suspend fun getPosts(section: String, page: Int): Root

}