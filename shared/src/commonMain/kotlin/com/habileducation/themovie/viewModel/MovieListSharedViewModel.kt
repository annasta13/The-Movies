package com.habileducation.themovie.viewModel

import com.habileducation.themovie.AppKey
import com.habileducation.themovie.useCase.fetchMovies.FetchMovies
import com.habileducation.themovie.util.CommonFlow
import com.habileducation.themovie.util.asCommonFlow
import com.habileducation.themovie.viewState.MovieListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */
class MovieListSharedViewModel(private val fetchMovies: FetchMovies) {

    fun loadMovie(url: String, page: Int): CommonFlow<MovieListState> = flow {
        val result = fetchMovies.loadMovie(url = url, page = page).last()
        emit(
            MovieListState(
                movieResponse = result.getOrNull(),
                error = result.exceptionOrNull()
            )
        )
    }.asCommonFlow()
}

object MovieType {
    const val POPULAR_MOVIES = 1
    const val NOW_PLAYING_MOVIES = 2
    const val UPCOMING_MOVIES = 3
    const val TOP_RATED_MOVIES = 4

    fun url(type: Int) = when (type) {
        POPULAR_MOVIES -> AppKey.POPULAR_MOVIES
        NOW_PLAYING_MOVIES -> AppKey.NOW_PLAYING_MOVIES
        UPCOMING_MOVIES -> AppKey.UPCOMING_MOVIES
        else -> AppKey.TOP_RATED_MOVIES
    }
}