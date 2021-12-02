package com.habileducation.themovie.data.model.local

import com.habileducation.themovie.AppKey

/**
 * Created by Annas Surdyanto on 15/11/21.
 *
 */
data class MovieCategory(
    val id: Int,
    val type: String,
    val name: String
) {
    constructor() : this(0, "", "")

    companion object {
        private val popular = MovieCategory(id = 1, type = AppKey.POPULAR_MOVIES, name = "Popular")
        private val upComing =
            MovieCategory(id = 2, type = AppKey.UPCOMING_MOVIES, name = "UpComing")
        private val topRated =
            MovieCategory(id = 3, type = AppKey.TOP_RATED_MOVIES, name = "Top Rated")
        private val nowPlaying =
            MovieCategory(id = 4, type = AppKey.NOW_PLAYING_MOVIES, name = "Now Playing")
        val movieCategory: List<MovieCategory> = listOf(popular, upComing, topRated, nowPlaying)
    }
}