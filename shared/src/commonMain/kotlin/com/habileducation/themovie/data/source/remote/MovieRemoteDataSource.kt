package com.habileducation.themovie.data.source.remote

import com.habileducation.themovie.data.model.remote.MovieDetailAndReview
import com.habileducation.themovie.data.model.remote.MovieResponseDto
import kotlinx.coroutines.flow.Flow

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */
interface MovieRemoteDataSource {
    suspend fun loadMovie(url: String, page: Int): MovieResponseDto
    suspend fun fetchMovieDetailAndReview(movieId: Long): MovieDetailAndReview
}