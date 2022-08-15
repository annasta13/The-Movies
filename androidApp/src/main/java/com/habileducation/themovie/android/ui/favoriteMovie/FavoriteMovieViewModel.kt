package com.habileducation.themovie.android.ui.favoriteMovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.habileducation.themovie.viewModel.FavoriteMovieSharedViewModel
import com.habileducation.themovie.viewState.FavoriteMovieViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */

@HiltViewModel
class FavoriteMovieViewModel @Inject constructor(
    private val sharedViewModel: FavoriteMovieSharedViewModel,
    private val dispatcher: CoroutineDispatcher
) :
    ViewModel() {

    var viewState = MutableStateFlow(FavoriteMovieViewState.empty)

    init {
        initState()
    }

    fun initState() {
        viewModelScope.launch(dispatcher) {
            viewState.value = sharedViewModel.getFavoriteMovie(viewState = viewState.value).last()
        }
    }
}