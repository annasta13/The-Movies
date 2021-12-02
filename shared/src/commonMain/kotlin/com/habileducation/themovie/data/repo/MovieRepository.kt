package com.habileducation.themovie.data.repo

import com.habileducation.themovie.data.model.local.Movie
import com.habileducation.themovie.data.model.remote.MovieDetailAndReview
import com.habileducation.themovie.data.model.remote.MovieResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */

interface MovieRepository {
    fun fetchMovies(movieType: String): Flow<Result<MovieResponse>>
    fun fetchMovieDetail(movieId: Long): Flow<Result<MovieDetailAndReview?>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    suspend fun isFavorited(movieId: Long): Boolean
    suspend fun setFavorite(movie: Movie): Boolean
}