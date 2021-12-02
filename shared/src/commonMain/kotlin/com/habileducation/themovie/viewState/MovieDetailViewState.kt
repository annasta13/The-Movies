package com.habileducation.themovie.viewState

import com.habileducation.themovie.data.model.remote.MovieDetailAndReview

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */
data class MovieDetailViewState(
    val movieResponse: MovieDetailAndReview?,
    val isFavorited: Boolean,
    val error: Throwable?
) {
    val isLoading: Boolean get() = movieResponse == null && error == null

    companion object {
        val empty = MovieDetailViewState(
            null,
            false,
            null
        )
        val mocked = MovieDetailViewState(
            MovieDetailAndReview.mocked,
            true,
            null
        )
    }
}

fun MovieDetailViewState.resetState(): MovieDetailViewState {
    return this.copy(error = null)
}