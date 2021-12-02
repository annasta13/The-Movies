package com.habileducation.themovie.useCase

import com.habileducation.themovie.AppKey
import com.habileducation.themovie.data.model.remote.MovieResponse
import com.habileducation.themovie.util.asMovieResponse
import com.habileducation.themovie.util.popularMovies
import com.habileducation.themovie.util.runBlocking
import kotlinx.coroutines.flow.first
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */
class FetchMoviesTest {

    private val  fakeFetchMovie = FakeFetchMovies()
    @Test
    fun fetchMovieShouldReturnFailure() = runBlocking {

        //when
        val actual = fakeFetchMovie.invoke("").first()

        //expected null
        assertEquals(actual.getOrNull(), null)
    }

    @Test
    fun fetchMovieShouldReturnSuccess() = runBlocking {
        //when
        val actual = fakeFetchMovie.invoke(AppKey.POPULAR_MOVIES).first()

        //expected
        val expected = popularMovies.asMovieResponse()

        assertEquals(actual.getOrNull(), expected)
    }
}