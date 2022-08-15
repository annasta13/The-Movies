package com.habileducation.themovie.viewState

import com.habileducation.themovie.data.model.remote.MovieResponse

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */
data class MovieListState(
    val movieResponse: MovieResponse?,
    val error: Throwable?
) {


    companion object {
        val empty = MovieListState(
            null,
            null
        )
    }
}

fun MovieListState.resetState(): MovieListState {
    return this.copy(error = null, movieResponse = null)
}