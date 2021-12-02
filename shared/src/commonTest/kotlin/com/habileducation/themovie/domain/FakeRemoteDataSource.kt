package com.habileducation.themovie.domain

import com.habileducation.themovie.AppKey
import com.habileducation.themovie.data.model.remote.MovieDetailAndReview
import com.habileducation.themovie.data.model.remote.MovieDetailResponse
import com.habileducation.themovie.data.model.remote.MovieResponse
import com.habileducation.themovie.data.model.remote.ReviewResponse
import com.habileducation.themovie.data.source.remote.MovieRemoteDataSource
import com.habileducation.themovie.util.ApiKey
import com.habileducation.themovie.util.FakeApiService
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */
class FakeRemoteDataSource : MovieRemoteDataSource {
    private val apiService = FakeApiService()

    override fun fetchMovies(movieType: String): Flow<Result<MovieResponse>> = flow {
        val api = apiService.buildApiService(movieType)
        val response = api.get<MovieResponse>(urlString = "${AppKey.BASE_URL}$movieType") {
            contentType(ContentType.Application.Json)
            parameter("api_key", ApiKey().value)
        }
        emit(Result.success(response))
    }.catch { e -> emit(Result.failure(exception = e)) }

    override fun fetchMovieDetailAndReview(movieId: Long): Flow<Result<MovieDetailAndReview>> =
        flow {
            val movie = fetchMovieDetail(movieId)
            val review = fetchMovieReview(movieId)
            emit(Result.success(MovieDetailAndReview(movie, review)))
        }.catch { e -> emit(Result.failure(exception = e)) }

    private suspend fun fetchMovieDetail(movieId: Long): MovieDetailResponse? {
        val mockedParam = if(movieId > 0L) "movieDetail" else null
        val api = apiService.buildApiService(mockedParam!!)
        return api.get(urlString = AppKey.BASE_URL) {
            contentType(ContentType.Application.Json)
            parameter("api_key", ApiKey().value)
            url {
                encodedPath = "${AppKey.MOVIE_DETAIL}$movieId"
            }
        }
    }

    private suspend fun fetchMovieReview(movieId: Long): ReviewResponse? {
        val mockedParam = if(movieId > 0L) "movieReview" else null
        val api = apiService.buildApiService(mockedParam!!)
        return api.get(urlString = AppKey.BASE_URL) {
            contentType(ContentType.Application.Json)
            parameter("api_key", ApiKey().value)
            url {
                encodedPath = "${AppKey.MOVIE_DETAIL}$movieId/${AppKey.MOVIE_REVIEW}"
            }
        }
    }
}