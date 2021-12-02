package com.habileducation.themovie.remote

import com.habileducation.themovie.AppKey
import com.habileducation.themovie.data.model.remote.MovieResponse
import com.habileducation.themovie.util.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Created by Annas Surdyanto on 28/11/21.
 *
 */
class FakeApiServiceTest {
    private val fakeApiService = FakeApiService()

    @Test
    fun testGetPopularMovieResponse() = runBlocking {
        val api = fakeApiService.buildApiService(AppKey.POPULAR_MOVIES)
        val response =
            api.get<MovieResponse>(urlString = "${AppKey.BASE_URL}${AppKey.POPULAR_MOVIES}") {
                contentType(ContentType.Application.Json)
                parameter("api_key", ApiKey().value)
            }
        val expected = popularMovies.asMovieResponse()
        assertEquals(expected = expected, actual = response)
    }

    @Test
    fun testGetMovieDetail() = runBlocking {
        val api = fakeApiService.buildApiService(AppKey.POPULAR_MOVIES)
        val response =
            api.get<MovieResponse>(urlString = "${AppKey.BASE_URL}${AppKey.POPULAR_MOVIES}") {
                contentType(ContentType.Application.Json)
                parameter("api_key", ApiKey().value)
            }
        val expected = popularMovies.asMovieResponse()
        assertEquals(expected = expected, actual = response)
    }
}