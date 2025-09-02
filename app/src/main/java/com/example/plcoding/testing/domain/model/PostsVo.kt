package com.example.plcoding.testing.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class PostsVo(
    val body: String,
    val title: String,
    val id: Int,
    val userId: Int
): Parcelable
