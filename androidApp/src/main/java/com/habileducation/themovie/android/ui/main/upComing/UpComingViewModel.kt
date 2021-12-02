package com.habileducation.themovie.android.ui.main.upComing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.habileducation.themovie.viewModel.MovieSharedViewModel
import com.habileducation.themovie.viewState.MovieViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */

@HiltViewModel
class UpComingViewModel @Inject constructor(private val sharedViewModel: MovieSharedViewModel) :
    ViewModel() {

    var viewState = MutableStateFlow(MovieViewState.empty)

    init {
        initState()
    }

    fun initState() {
        viewModelScope.launch(Dispatchers.IO) {
            sharedViewModel.fetchMovie(viewState = viewState.value, 3).collect {
                viewState.value = it
            }
        }
    }
}