package com.example.plcoding.testing.domain.repository

import androidx.paging.Pager
import com.example.plcoding.testing.data.utils.Resource
import com.example.plcoding.testing.domain.model.PostsVo
import kotlinx.coroutines.flow.Flow

interface GetPostRepository {

    fun getPosts(): Flow<Resource<List<PostsVo>>>

    suspend fun refreshPosts()
}