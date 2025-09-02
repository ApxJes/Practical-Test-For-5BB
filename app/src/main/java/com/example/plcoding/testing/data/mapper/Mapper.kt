package com.example.plcoding.testing.data.mapper

import com.example.plcoding.testing.data.local.PostEntity
import com.example.plcoding.testing.data.remote.PostsResponse
import com.example.plcoding.testing.domain.model.PostsVo

fun PostEntity.toDomain(): PostsVo {
    return PostsVo(
        body = body,
        title = title,
        id = id ?: 0,
        userId = userId
    )
}

fun PostsResponse.toEntity(): PostEntity {
    return PostEntity(
        id = this.id ?: 0,
        title = this.title.orEmpty(),
        body = this.body.orEmpty(),
        userId = this.userId ?: 0
    )
}