package com.habileducation.themovie.data.model.local

/**
 * Created by Annas Surdyanto on 13/11/21.
 *
 */

data class Review(
    val id: String,
    val author: String,
    val content: String,
    val avatarPath: String,
    val rating: Double,
    val movieId: Long
)

