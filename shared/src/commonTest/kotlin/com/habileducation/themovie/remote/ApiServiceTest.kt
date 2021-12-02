/*
package com.habileducation.themovie.remote

import com.habileducation.themovie.AppKey
import com.habileducation.themovie.data.model.remote.MovieDetailResponse
import com.habileducation.themovie.data.model.remote.MovieResponse
import com.habileducation.themovie.data.model.remote.ReviewResponse
import com.habileducation.themovie.util.ApiKey
import com.habileducation.themovie.util.runBlocking
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlin.test.Test
import kotlin.test.assertTrue

*/
/**
 * Created by Annas Surdyanto on 08/09/21.
 *
 *//*


class ApiServiceTest {
    //url = https://developers.themoviedb.org/3/movies/get-movie-details
    private val api = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true // if the server sends extra fields, ignore them
                }
            )
        }
    }

    @Test
    fun fetchPopularMoviesShouldReturnSuccess() = runBlocking {
        val actual =
            api.get<MovieResponse>(urlString = "${AppKey.BASE_URL}${AppKey.POPULAR_MOVIES}") {
                contentType(ContentType.Application.Json)
                parameter("api_key", ApiKey().value)
            }
        val expected = actual.movieList.isNotEmpty()
        assertTrue(expected)
    }

    @Test
    fun fetchUpComingMoviesShouldReturnSuccess() = runBlocking {
        val actual =
            api.get<MovieResponse>(urlString = "${AppKey.BASE_URL}${AppKey.UPCOMING_MOVIES}") {
                contentType(ContentType.Application.Json)
                parameter("api_key", ApiKey().value)
            }
        val expected = actual.movieList.isNotEmpty()
        assertTrue(expected)
    }

    @Test
    fun fetchTopRatedMoviesShouldReturnSuccess() = runBlocking {
        val actual =
            api.get<MovieResponse>(urlString = "${AppKey.BASE_URL}${AppKey.TOP_RATED_MOVIES}") {
                contentType(ContentType.Application.Json)
                parameter("api_key", ApiKey().value)
            }
        val expected = actual.movieList.isNotEmpty()
        assertTrue(expected)
    }

    @Test
    fun fetchNowPlayingMoviesShouldReturnSuccess() = runBlocking {
        val actual =
            api.get<MovieResponse>(urlString = "${AppKey.BASE_URL}${AppKey.NOW_PLAYING_MOVIES}") {
                contentType(ContentType.Application.Json)
                parameter("api_key", ApiKey().value)
            }
        val expected = actual.movieList.isNotEmpty()
        assertTrue(expected)
    }

    @Test
    fun fetchDetailMovieShouldReturnSuccess() = runBlocking {
        val movieId = 580489
        val actual = api.get<MovieDetailResponse?>(urlString = AppKey.BASE_URL) {
            contentType(ContentType.Application.Json)
            parameter("api_key", ApiKey().value)
            url {
                encodedPath = "${AppKey.MOVIE_DETAIL}$movieId"
            }
        }
        val expected = actual != null
        assertTrue(expected)
    }

    @Test
    fun fetchMovieReviewShouldReturnSuccess() = runBlocking {
        val movieId = 566525
        val actual = api.get<ReviewResponse?>(urlString = AppKey.BASE_URL) {
            contentType(ContentType.Application.Json)
            parameter("api_key", ApiKey().value)
            url {
                encodedPath = "${AppKey.MOVIE_DETAIL}$movieId/${AppKey.MOVIE_REVIEW}"
            }
        }
        val expected = actual != null
        assertTrue(expected)
    }
}*/
