package com.habileducation.themovie.viewModel

import com.habileducation.themovie.AppKey
import com.habileducation.themovie.data.model.remote.MovieResponse
import com.habileducation.themovie.useCase.FakeFetchMovies
import com.habileducation.themovie.util.CommonFlow
import com.habileducation.themovie.util.asCommonFlow
import com.habileducation.themovie.viewState.MovieViewState
import com.habileducation.themovie.viewState.resetState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */
class FakePopularMovieViewModel {

    fun fetchMovie(viewState: MovieViewState, movieType: String): CommonFlow<MovieViewState> =
        flow {
            val newState = viewState.resetState()
            val fakeFetchMovie = FakeFetchMovies()
            emit(newState.copy(isLoading = true))
            fakeFetchMovie.invoke(movieType).collect {
                val stateToEmit = newState.copy(
                    movieResponse = it.getOrNull(),
                    error = it.exceptionOrNull(),
                    isLoading = false
                )
                emit(stateToEmit)
            }
        }.asCommonFlow()
}