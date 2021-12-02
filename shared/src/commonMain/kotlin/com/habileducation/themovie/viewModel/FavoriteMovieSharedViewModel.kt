package com.habileducation.themovie.viewModel

import com.habileducation.themovie.useCase.getFavoriteMovie.GetFavoriteMovie
import com.habileducation.themovie.util.asCommonFlow
import com.habileducation.themovie.viewState.FavoriteMovieViewState
import com.habileducation.themovie.viewState.resetState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */
class FavoriteMovieSharedViewModel(private val getFavoriteMovie: GetFavoriteMovie) {

    fun getFavoriteMovie(viewState: FavoriteMovieViewState) = flow {
        val newState = viewState.resetState()
        emit(newState.copy(isLoading = true))
        getFavoriteMovie.invoke().collect {
            val stateToEmit = newState.copy(
                isLoading = false,
                movieResponse = it
            )
            emit(stateToEmit)
        }
    }.asCommonFlow()
}