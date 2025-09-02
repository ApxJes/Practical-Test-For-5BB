package com.example.plcoding.testing.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PostEntity::class],
    version = 1
)
abstract class PostsDatabase: RoomDatabase() {
    abstract fun postsDao(): PostDao
}