package com.habileducation.themovie.android.ui.movieDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.habileducation.themovie.viewModel.MovieDetailSharedViewModel
import com.habileducation.themovie.viewState.MovieDetailViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Annas Surdyanto on 18/11/21.
 *
 */

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val detailViewModel: MovieDetailSharedViewModel,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    val state = MutableStateFlow(MovieDetailViewState.empty)

    fun initState(movieId: Long) {
        viewModelScope.launch(dispatcher) {
            detailViewModel.fetchDetail(viewState = state.value, movieId).collect {
                state.value = it
            }
        }
    }

    fun setFavorite() {
        viewModelScope.launch(dispatcher) {
            detailViewModel.setFavorite(viewState = state.value).collect {
                state.value = it
            }
        }
    }
}