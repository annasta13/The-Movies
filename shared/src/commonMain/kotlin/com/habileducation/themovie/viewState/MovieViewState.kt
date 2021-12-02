package com.habileducation.themovie.viewState

import com.habileducation.themovie.data.model.local.MovieCategory
import com.habileducation.themovie.data.model.remote.MovieResponse

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */
data class MovieViewState(
    val isLoading: Boolean,
    val movieResponse: MovieResponse?,
    val error: Throwable?
) {


    companion object {
        val empty = MovieViewState(
            true,
            null,
            null
        )
        val mocked = MovieViewState(
            false,
            MovieResponse.mocked,
            null
        )
    }
}

fun MovieViewState.resetState(): MovieViewState {
    return this.copy(error = null)
}