package com.habileducation.themovie.domain

import com.habileducation.themovie.data.model.local.Movie
import com.habileducation.themovie.data.model.remote.MovieDetailResponse
import com.habileducation.themovie.data.model.remote.asDomainMovie
import com.habileducation.themovie.util.runBlocking
import kotlinx.coroutines.flow.first
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */
class LocalDataSourceTest {

    @Test
    fun getFavoriteMovie() = runBlocking {
        val given = listOf(Movie.mocked)
        val localDataSourceTest = FakeLocalDataSource(givenFavoriteMovie = given)
        val actual = localDataSourceTest.getFavoriteMovie().first()
        assertEquals(expected = given, actual = actual)
    }

    @Test
    fun insertFavorite() = runBlocking {
        val given = MovieDetailResponse.mocked.asDomainMovie()
        val localDataSourceTest = FakeLocalDataSource()
        localDataSourceTest.insertFavorite(movie = given)
        assertEquals(expected = given, actual = localDataSourceTest.favoriteInserted)
    }

    @Test
    fun deleteFavorite() = runBlocking {
        val given: Long = 34213
        val localDataSourceTest = FakeLocalDataSource()
        localDataSourceTest.deleteFavorite(movieId = given)
        assertEquals(expected = given,actual = localDataSourceTest.movieDeleted)
    }

}