package com.habileducation.themovie.data.source.local

import com.habileducation.movie.data.source.AppDatabase
import com.habileducation.themovie.data.factory.asFavoriteMovieList
import com.habileducation.themovie.data.model.local.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */
class MovieLocalDataSourceImpl(appDatabase: AppDatabase) : MovieLocalDataSource {
    private val operator = appDatabase.appDatabaseQueries

    override fun getFavoriteMovie(): Flow<List<Movie>> = flow {
        emit(operator.getAllFavorites().executeAsList().asFavoriteMovieList())
    }

    override suspend fun insertFavorite(movie: Movie) {
        operator.insertFavorite(
            movie_id = movie.movieId,
            poster_path = movie.posterPath,
            overview = movie.overview,
            release_date = movie.releaseDate,
            original_title = movie.originalTitle
        )
    }

    override suspend fun isFavorited(movieId: Long): Boolean {
        val isFavorited = operator.getFavoriteById(movieId).executeAsOneOrNull()
        return isFavorited != null
    }

    override suspend fun deleteFavorite(movieId: Long) {
        operator.deleteFavoriteMovie(movieId = movieId)
    }

    override suspend fun setFavorite(movie: Movie): Boolean {
        if (!isFavorited(movie.movieId)) insertFavorite(movie)
        else deleteFavorite(movie.movieId)
        return isFavorited(movieId = movie.movieId)
    }

}