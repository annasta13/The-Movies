package com.habileducation.themovie.data.remote

import com.habileducation.themovie.AppKey
import com.habileducation.themovie.data.model.remote.MovieDetailAndReview
import com.habileducation.themovie.data.model.remote.MovieDetailResponse
import com.habileducation.themovie.data.model.remote.MovieResponseDto
import com.habileducation.themovie.data.model.remote.ReviewResponse
import com.habileducation.themovie.data.source.remote.MovieRemoteDataSource
import com.habileducation.themovie.util.ApiKey
import com.habileducation.themovie.util.FakeApiService
import io.ktor.client.request.*
import io.ktor.http.*

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */
class FakeRemoteDataSource : MovieRemoteDataSource {
    private val apiService = FakeApiService()
    private var movieListResponse = ""
    private var movieDetailResponse = ""
    private var reviewResponse = ""

    fun setMovieListResponse(fileName: String) {
        movieListResponse = fileName
    }

    fun setMovieDetailResponse(fileName: String) {
        movieDetailResponse = fileName
    }

    fun setReviewResponse(fileName: String) {
        reviewResponse = fileName
    }

    override suspend fun loadMovie(url: String, page: Int): MovieResponseDto {
        val api = apiService.buildApiService(movieListResponse)
        return api.get(urlString = "${AppKey.BASE_URL}$url") {
            contentType(ContentType.Application.Json)
            parameter("api_key", ApiKey().value)
            parameter("page", page)
        }
    }

    override suspend fun fetchMovieDetailAndReview(movieId: Long): MovieDetailAndReview {
        val movie = fetchMovieDetail(movieId)
        val review = fetchMovieReview(movieId)
        return MovieDetailAndReview(movie, review)
    }

    private suspend fun fetchMovieDetail(movieId: Long): MovieDetailResponse {
        val api = apiService.buildApiService(movieDetailResponse)
        return api.get(urlString = AppKey.BASE_URL) {
            contentType(ContentType.Application.Json)
            parameter("api_key", ApiKey().value)
            url {
                encodedPath = "${AppKey.MOVIE_DETAIL}$movieId"
            }
        }
    }

    private suspend fun fetchMovieReview(movieId: Long): ReviewResponse {
        val api = apiService.buildApiService(reviewResponse)
        return api.get(urlString = AppKey.BASE_URL) {
            contentType(ContentType.Application.Json)
            parameter("api_key", ApiKey().value)
            url {
                encodedPath = "${AppKey.MOVIE_DETAIL}$movieId/${AppKey.MOVIE_REVIEW}"
            }
        }
    }
}