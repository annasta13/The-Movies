package com.habileducation.themovie.android.ui.movieDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.habileducation.themovie.android.ui.navGraph.AppArgument
import com.habileducation.themovie.viewModel.MovieDetailSharedViewModel
import com.habileducation.themovie.viewState.MovieDetailViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Annas Surdyanto on 18/11/21.
 *
 */

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val detailViewModel: MovieDetailSharedViewModel) : ViewModel() {
    val state = MutableStateFlow(MovieDetailViewState.empty)

    fun initState(movieId: Long) {
        viewModelScope.launch {
            detailViewModel.fetchDetail(viewState = state.value, movieId).collect {
                state.value = it
            }
        }
    }

    fun setFavorite() {
        viewModelScope.launch {
            detailViewModel.setFavorite(viewState = state.value).collect {
                state.value = it
            }
        }
    }
}