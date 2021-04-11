package com.example.developerslife.model.datasource

import com.example.developerslife.data.entity.Root
import com.example.developerslife.model.retrofit.RetrofitService

class RemoteDataSourceImplementation(private val api: RetrofitService) : RemoteDataSource {

    override suspend fun getPosts(section: String, page: Int): Root {
        return api.getPostsAsync(section, page).await()
    }

}