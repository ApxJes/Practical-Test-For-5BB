package com.example.plcoding.testing.di

import android.content.Context
import androidx.room.Room
import com.example.plcoding.testing.data.local.PostDao
import com.example.plcoding.testing.data.local.PostsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesRoom(
        @ApplicationContext context: Context
    ): PostsDatabase {
        return Room.databaseBuilder(
            context,
            PostsDatabase::class.java,
            "posts_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesDao(db: PostsDatabase) = db.postsDao()
}