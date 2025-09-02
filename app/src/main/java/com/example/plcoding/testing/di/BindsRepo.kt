package com.example.plcoding.testing.di

import com.example.plcoding.testing.data.repository.GetPostsRepositoryImpl
import com.example.plcoding.testing.domain.repository.GetPostRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsRepo {

    @Binds
    @Singleton
    abstract fun bindsRepository(
        postsRepositoryImpl: GetPostsRepositoryImpl
    ): GetPostRepository
}