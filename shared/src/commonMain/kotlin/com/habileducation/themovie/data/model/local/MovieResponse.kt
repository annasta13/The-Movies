package com.habileducation.themovie.data.model.local

/**
 * Created by Annas Surdyanto on 16/08/22.
 *
 */
data class MovieResponse(
    val movieList: List<Movie>,
    val page: Int,
    val message: String?,
    val totalPages: Int,
)