package com.habileducation.themovie.data.factory

import com.habileducation.movie.data.source.AppDatabase
import com.habileducation.themovie.data.model.local.Movie
import com.squareup.sqldelight.db.SqlDriver
import comhabileducationmoviedatasource.Favorite_entity

/**
 * Created by Annas Surdyanto on 06/09/21.
 *
 */

class AppDatabaseFactory(private val databaseFactory: DatabaseFactory) {
    fun createDatabase(): AppDatabase {
        return AppDatabase(databaseFactory.createDatabase())
    }
}

expect class DatabaseFactory {
    fun createDatabase(): SqlDriver
}

fun Favorite_entity.asMovie(): Movie {
    return Movie(
        posterPath = poster_path,
        overview = overview,
        releaseDate = release_date,
        movieId = movie_id,
        originalTitle = original_title
    )
}

fun List<Favorite_entity>.asFavoriteMovieList(): List<Movie> {
    return map { it.asMovie() }
}