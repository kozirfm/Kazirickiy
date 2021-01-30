package com.example.developerslife.model.repository

import com.example.developerslife.data.entity.Root
import com.example.developerslife.model.datasource.RemoteDataSource

class RepositoryImplementation(private val remoteDataSource: RemoteDataSource) : Repository {

    override suspend fun getPosts(section: String, page: Int): Root {
        return remoteDataSource.getPosts(section, page)
    }

}