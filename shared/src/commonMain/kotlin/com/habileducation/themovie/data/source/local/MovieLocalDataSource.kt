package com.habileducation.themovie.data.source.local

import com.habileducation.themovie.data.model.local.Movie
import com.habileducation.themovie.data.model.remote.MovieData
import kotlinx.coroutines.flow.Flow

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */
interface MovieLocalDataSource {
    fun getFavoriteMovie(): Flow<List<Movie>>
    suspend fun insertFavorite(movie: Movie)
    suspend fun isFavorited(movieId: Long): Boolean
    suspend fun deleteFavorite(movieId: Long)
    suspend fun setFavorite(movie: Movie): Boolean
}