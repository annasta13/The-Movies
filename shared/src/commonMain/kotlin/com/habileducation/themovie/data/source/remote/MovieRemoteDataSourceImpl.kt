package com.habileducation.themovie.data.source.remote

import com.habileducation.themovie.AppKey
import com.habileducation.themovie.data.model.remote.MovieDetailAndReview
import com.habileducation.themovie.data.model.remote.MovieDetailResponse
import com.habileducation.themovie.data.model.remote.MovieResponse
import com.habileducation.themovie.data.model.remote.ReviewResponse
import com.habileducation.themovie.util.ApiKey
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */

class MovieRemoteDataSourceImpl(private val apiService: HttpClient) : MovieRemoteDataSource {
    override fun loadMovie(url: String, page: Int): Flow<Result<MovieResponse>> = flow {
        val response =
            apiService.get<MovieResponse>(urlString = "${AppKey.BASE_URL}$url") {
                contentType(ContentType.Application.Json)
                parameter("api_key", ApiKey().value)
                parameter("page", page)
            }
        emit(Result.success(response))
    }.catch { e -> emit(Result.failure(e)) }

    override fun fetchMovieDetailAndReview(movieId: Long): Flow<Result<MovieDetailAndReview?>> =
        flow {
            val movie = fetchMovieDetail(movieId)
            val review = fetchMovieReview(movieId)
            emit(Result.success(MovieDetailAndReview(movie, review)))
        }.catch { e -> emit(Result.failure(e)) }

    private suspend fun fetchMovieDetail(movieId: Long): MovieDetailResponse? {
        return apiService.get(urlString = AppKey.BASE_URL) {
            contentType(ContentType.Application.Json)
            parameter("api_key", ApiKey().value)
            url {
                encodedPath = "${AppKey.MOVIE_DETAIL}$movieId"
            }
        }
    }

    private suspend fun fetchMovieReview(movieId: Long): ReviewResponse? {
        return apiService.get(urlString = AppKey.BASE_URL) {
            contentType(ContentType.Application.Json)
            parameter("api_key", ApiKey().value)
            url {
                encodedPath = "${AppKey.MOVIE_DETAIL}$movieId/${AppKey.MOVIE_REVIEW}"
            }
        }
    }

}