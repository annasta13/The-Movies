package com.habileducation.themovie.viewModel

import com.habileducation.themovie.AppKey
import com.habileducation.themovie.useCase.fetchMovies.FetchMovies
import com.habileducation.themovie.util.CommonFlow
import com.habileducation.themovie.util.asCommonFlow
import com.habileducation.themovie.viewState.MovieViewState
import com.habileducation.themovie.viewState.resetState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */
class MovieSharedViewModel(private val fetchMovies: FetchMovies) {

    fun fetchMovie(viewState: MovieViewState, type: Int): CommonFlow<MovieViewState> = flow {
        val newState = viewState.resetState()
        emit(newState.copy(isLoading = true))
        fetchMovies.invoke(getUrl(type)).collect {
            val stateToEmit = newState.copy(
                isLoading = false,
                movieResponse = it.getOrDefault(viewState.movieResponse),
                error = it.exceptionOrNull()
            )
            emit(stateToEmit)
        }
    }.asCommonFlow()

    private fun getUrl(type: Int): String {
        return when (type) {
            1 -> AppKey.POPULAR_MOVIES
            2 -> AppKey.NOW_PLAYING_MOVIES
            3 -> AppKey.UPCOMING_MOVIES
            else -> AppKey.TOP_RATED_MOVIES
        }
    }
}