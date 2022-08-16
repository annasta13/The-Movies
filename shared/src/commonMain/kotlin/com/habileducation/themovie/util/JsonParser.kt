package com.habileducation.themovie.util

import com.habileducation.themovie.data.model.remote.MovieDetailAndReview
import com.habileducation.themovie.data.model.remote.MovieDetailResponse
import com.habileducation.themovie.data.model.remote.MovieResponseDto
import com.habileducation.themovie.data.model.remote.ReviewResponse
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

/**
 * Created by Annas Surdyanto on 21/01/22.
 *
 */

@Suppress("OPT_IN_IS_NOT_ENABLED")
@OptIn(ExperimentalSerializationApi::class)
class JsonParser {
private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    fun <T> decodeToObject(resource: String, deserializer: DeserializationStrategy<T>): T {
        return json.decodeFromString(deserializer, resource)
    }

    fun <T> decodeToString(resource: T, deserializer: SerializationStrategy<T>): String {
        return json.encodeToString(deserializer, resource)
    }

    fun decodeMovieListResponse(response: String): MovieResponseDto{
        return decodeToObject(response, MovieResponseDto.serializer())
    }

    fun decodeMovieDetailAndReview(movieDetail: String, movieReview: String): MovieDetailAndReview {
        val movieDetailResponse = decodeToObject(movieDetail, MovieDetailResponse.serializer())
        val movieReviewResponse = decodeToObject(movieReview, ReviewResponse.serializer())
        return MovieDetailAndReview(movieDetailResponse, movieReviewResponse)
    }
}