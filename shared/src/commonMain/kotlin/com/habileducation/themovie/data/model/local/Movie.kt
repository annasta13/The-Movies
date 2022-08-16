package com.habileducation.themovie.data.model.local

import com.habileducation.themovie.data.model.remote.MovieDataDto
import com.habileducation.themovie.data.model.remote.asDomainModel

/**
 * Created by Annas Surdyanto on 13/11/21.
 *
 */
data class Movie(
    val posterPath: String,
    val overview: String,
    val releaseDate: String,
    val movieId: Long,
    val originalTitle: String,
) {
    val poster = "https://image.tmdb.org/t/p/w300/$posterPath"
    companion object {
        val mocked = MovieDataDto.mocked.asDomainModel()
    }
}