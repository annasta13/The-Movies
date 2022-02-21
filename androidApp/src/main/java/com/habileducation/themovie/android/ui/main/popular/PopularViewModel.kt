package com.habileducation.themovie.android.ui.main.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.habileducation.themovie.viewModel.MovieSharedViewModel
import com.habileducation.themovie.viewState.MovieViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val sharedViewModel: MovieSharedViewModel,
    private val dispatcher: CoroutineDispatcher
) :
    ViewModel() {

    var viewState = MutableStateFlow(MovieViewState.empty)

    init {
        initState()
    }

    fun initState() {
        viewModelScope.launch(dispatcher) {
            sharedViewModel.fetchMovie(viewState = viewState.value, 1).collect {
                viewState.value = it
            }
        }
    }
}