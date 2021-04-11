package com.example.developerslife.model.retrofit

import com.example.developerslife.data.entity.Root
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("{section}/{page}?json=true")
    fun getPostsAsync(
        @Path("section") section: String,
        @Path("page") page: Int
    ): Deferred<Root>

}