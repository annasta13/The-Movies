package com.habileducation.themovie.data.source.remote

import com.habileducation.themovie.data.model.remote.MovieDetailAndReview
import com.habileducation.themovie.data.model.remote.MovieResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */
interface MovieRemoteDataSource {
    fun loadMovie(url: String, page: Int): Flow<Result<MovieResponse>>
    fun fetchMovieDetailAndReview(movieId: Long): Flow<Result<MovieDetailAndReview?>>
}