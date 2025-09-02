package com.example.plcoding.testing.domain.usecase

import com.example.plcoding.testing.domain.repository.GetPostRepository
import javax.inject.Inject

class RefreshPostsUseCase @Inject constructor(
    private val repository: GetPostRepository
) {
    suspend operator fun invoke() {
        repository.refreshPosts()
    }
}
