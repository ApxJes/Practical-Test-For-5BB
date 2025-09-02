package com.example.plcoding.testing.data.repository

import com.example.plcoding.testing.data.local.PostDao
import com.example.plcoding.testing.data.mapper.toDomain
import com.example.plcoding.testing.data.mapper.toEntity
import com.example.plcoding.testing.data.remote.PostsApi
import com.example.plcoding.testing.data.utils.Resource
import com.example.plcoding.testing.domain.model.PostsVo
import com.example.plcoding.testing.domain.repository.GetPostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetPostsRepositoryImpl @Inject constructor(
    private val api: PostsApi,
    private val postsDao: PostDao
): GetPostRepository {

    override fun getPosts(): Flow<Resource<List<PostsVo>>> = flow{
        emit(Resource.Loading())

        try {
            val response = api.getPosts()
            val entities = response.map { it.toEntity() }

            postsDao.clearPosts()
            postsDao.insertPosts(entities)


        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Try again! Please check your internet connection"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Oops! Something went wrong"))
        }

        postsDao.getAllPosts().collect { list->
            val domainPost = list.map {
                it.toDomain()
            }
            emit(Resource.Success(data = domainPost))
        }
    }

    override suspend fun refreshPosts() {
        try {
            val response = api.getPosts()
            val entities = response.map { it.toEntity() }

            postsDao.clearPosts()
            postsDao.insertPosts(entities)
        } catch (e: Exception) {
            throw e
        }
    }
}