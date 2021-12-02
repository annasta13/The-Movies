package com.habileducation.themovie.util

import com.habileducation.themovie.data.model.remote.MovieDetailResponse
import com.habileducation.themovie.data.model.remote.MovieResponse
import com.habileducation.themovie.data.model.remote.ReviewResponse
import kotlinx.serialization.json.Json

/**
 * Created by Annas Surdyanto on 15/11/21.
 *
 */

fun Result<Any>.isLoading(): Boolean {
    return !this.isFailure && !this.isSuccess
}

fun Result<Any>.loading(): Boolean {
    return true
}

fun String.asMovieResponse() : MovieResponse {
    return Json{ ignoreUnknownKeys = true }.decodeFromString(MovieResponse.serializer(), this)
}

fun String.asMovieDetailResponse() : MovieDetailResponse {
    return Json{ ignoreUnknownKeys = true }.decodeFromString(MovieDetailResponse.serializer(), this)
}

fun String.asMovieReviewResponse() : ReviewResponse {
    return Json{ ignoreUnknownKeys = true }.decodeFromString(ReviewResponse.serializer(), this)
}