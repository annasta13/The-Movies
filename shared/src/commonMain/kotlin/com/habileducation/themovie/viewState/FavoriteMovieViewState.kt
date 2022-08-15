package com.habileducation.themovie.viewState

import com.habileducation.themovie.data.model.local.Movie

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */
data class FavoriteMovieViewState(
    val isLoading: Boolean,
    val movieResponse: List<Movie>,
    val error: Throwable?
) {


    companion object {
        val empty = FavoriteMovieViewState(
            true,
            emptyList(),
            null
        )
        val mocked = FavoriteMovieViewState(
            false,
            listOf(Movie.mocked),
            null
        )
    }
}

fun FavoriteMovieViewState.resetState(isLoading : Boolean = false): FavoriteMovieViewState {
    return this.copy(error = null, isLoading = isLoading)
}