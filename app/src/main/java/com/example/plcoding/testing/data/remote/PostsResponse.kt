package com.example.plcoding.testing.data.remote


import com.google.gson.annotations.SerializedName

data class PostsResponse(
    @SerializedName("body")
    val body: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("userId")
    val userId: Int?
)