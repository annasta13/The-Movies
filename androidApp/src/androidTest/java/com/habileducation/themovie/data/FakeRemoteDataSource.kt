package com.habileducation.themovie.data

import com.habileducation.themovie.data.model.remote.MovieDetailAndReview
import com.habileducation.themovie.data.model.remote.MovieResponse
import com.habileducation.themovie.data.source.remote.MovieRemoteDataSource
import com.habileducation.themovie.util.asMovieDetailResponse
import com.habileducation.themovie.util.asMovieResponse
import com.habileducation.themovie.util.asMovieReviewResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Annas Surdyanto on 23/11/21.
 *
 */
class FakeRemoteDataSource : MovieRemoteDataSource {
    override fun loadMovie(url: String, page: Int): Flow<Result<MovieResponse>> {
        TODO("Not yet implemented")
    }

    override fun fetchMovieDetailAndReview(movieId: Long): Flow<Result<MovieDetailAndReview?>> =
        flow {
            val detailString = this::class.java.classLoader!!.getResourceAsStream("movie_detail_19404.json").bufferedReader().use { it.readText() }
            val reviewString = this::class.java.classLoader!!.getResourceAsStream("reviews_19404.json").bufferedReader().use { it.readText() }
            val response = MovieDetailAndReview(detailString.asMovieDetailResponse(), reviewString.asMovieReviewResponse())
            emit(Result.success(response))
        }

    private fun jsonFile(movieType: String): String {
        return when (movieType) {
            "popular" -> "popular_movies.json"
            "top_rated" -> "top_rated.json"
            "upcoming" -> "upcoming.json"
            else -> "now_playing.json"
        }
    }
}