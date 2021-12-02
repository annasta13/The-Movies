package com.habileducation.themovie.data.model.remote

import com.habileducation.themovie.data.model.local.LoadingProgress

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */

data class MovieDetailRemoteData(
    val progress: LoadingProgress?,
    val response: MovieDetailAndReview?
) {
    companion object {
        val mocked = MovieDetailRemoteData(progress = null, response = MovieDetailAndReview.mocked)
    }
}

data class MovieDetailAndReview(
    val movieDetail: MovieDetailResponse?,
    val review: ReviewResponse?
) {
    companion object {
        val mocked = MovieDetailAndReview(
            movieDetail = MovieDetailResponse.mocked,
            review = ReviewResponse.mocked
        )

        val empty = MovieDetailAndReview(movieDetail = null, review = null)
    }
}
