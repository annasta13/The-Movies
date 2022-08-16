package com.habileducation.themovie.data.repo

import com.habileducation.themovie.data.model.local.Movie
import com.habileducation.themovie.data.model.local.MovieResponse
import com.habileducation.themovie.data.model.remote.MovieDetailAndReview
import com.habileducation.themovie.data.model.remote.MovieResponseDto
import kotlinx.coroutines.flow.Flow

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */

interface MovieRepository {
    fun fetchMovieDetail(movieId: Long): Flow<Result<MovieDetailAndReview?>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    suspend fun isFavorited(movieId: Long): Boolean
    suspend fun setFavorite(movie: Movie): Boolean
    fun loadMovie(url: String, page: Int): Flow<Result<MovieResponse>>
}