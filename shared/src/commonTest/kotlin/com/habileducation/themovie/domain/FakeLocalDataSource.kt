package com.habileducation.themovie.domain

import com.habileducation.themovie.data.model.local.Movie
import com.habileducation.themovie.data.source.local.MovieLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */
class FakeLocalDataSource(private val givenFavoriteMovie : List<Movie>? = null) : MovieLocalDataSource {
    var movieInserted : Movie? = null
    var favoriteInserted : Movie? = null
    var movieDeleted : Long? = null

    override fun getFavoriteMovie(): Flow<List<Movie>> = flow{
        emit(givenFavoriteMovie!!)
    }

    override suspend fun insertFavorite(movie: Movie) {
        favoriteInserted = movie
    }

    override suspend fun isFavorited(movieId: Long): Boolean {
        return false
    }

    override suspend fun deleteFavorite(movieId: Long) {
        movieDeleted = movieId
    }

    override suspend fun setFavorite(movie: Movie): Boolean {
        return false
    }
}