package com.example.plcoding.testing.data.remote

import retrofit2.http.GET

interface PostsApi {

    @GET("posts")
    suspend fun getPosts(): List<PostsResponse>
}