package com.example.plcoding.testing.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plcoding.testing.data.utils.Resource
import com.example.plcoding.testing.domain.model.PostsVo
import com.example.plcoding.testing.domain.usecase.GetPostsUseCase
import com.example.plcoding.testing.domain.usecase.RefreshPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val refreshPostsUseCase: RefreshPostsUseCase
): ViewModel(){

    val posts: StateFlow<Resource<List<PostsVo>>> =
        getPostsUseCase()
            .stateIn(viewModelScope, SharingStarted.Lazily, Resource.Loading())

    fun refresh() {
        viewModelScope.launch {
            refreshPostsUseCase()
        }
    }
}