package com.habileducation.themovie.viewModel

import com.habileducation.themovie.data.model.remote.asDomainMovie
import com.habileducation.themovie.useCase.checkFavoriteStatus.CheckFavoriteStatus
import com.habileducation.themovie.useCase.fetchDetail.FetchDetail
import com.habileducation.themovie.useCase.insertFavorite.SetFavorite
import com.habileducation.themovie.util.asCommonFlow
import com.habileducation.themovie.viewState.MovieDetailViewState
import com.habileducation.themovie.viewState.resetState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */
class MovieDetailSharedViewModel(
    private val fetchDetail: FetchDetail,
    private val checkFavoriteStatus: CheckFavoriteStatus,
    private val setFavorite: SetFavorite
) {

    fun fetchDetail(viewState: MovieDetailViewState, movieId: Long) = flow {
        val newState = viewState.resetState()
        val isFavorited = checkFavoriteStatus.isFavorited(movieId)
        fetchDetail.invoke(movieId).collect {
            val stateToEmit = newState.copy(
                movieResponse = it.getOrNull(),
                isFavorited = isFavorited,
                error = it.exceptionOrNull()
            )
            emit(stateToEmit)
        }
    }.asCommonFlow()

    fun setFavorite(viewState: MovieDetailViewState) = flow {
        val movie = viewState.movieResponse?.movieDetail
        val result = setFavorite.invoke(movie!!.asDomainMovie())
        val stateToEmit = viewState.copy(isFavorited = result)
        emit(stateToEmit)
    }.asCommonFlow()
}