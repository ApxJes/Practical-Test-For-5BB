package com.example.plcoding.testing.domain.usecase

import com.example.plcoding.testing.data.utils.Resource
import com.example.plcoding.testing.domain.model.PostsVo
import com.example.plcoding.testing.domain.repository.GetPostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: GetPostRepository
) {
    operator fun invoke(): Flow<Resource<List<PostsVo>>> {
        return repository.getPosts()
    }
}