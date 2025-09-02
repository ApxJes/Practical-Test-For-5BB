package com.example.plcoding.testing.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey val id: Int? = null,
    val userId: Int,
    val title: String,
    val body: String
)
