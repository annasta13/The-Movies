package com.habileducation.themovie.android.ui.main.nowPlaying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.habileducation.themovie.android.helper.MovieListPagingSource
import com.habileducation.themovie.data.model.local.Movie
import com.habileducation.themovie.viewModel.MovieListSharedViewModel
import com.habileducation.themovie.viewModel.MovieType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */

@HiltViewModel
class NowPlayingViewModel @Inject constructor(
    private val sharedViewModel: MovieListSharedViewModel
) : ViewModel() {
    var list: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = 1)) {
        MovieListPagingSource(
            sharedViewModel,
            MovieType.url(MovieType.NOW_PLAYING_MOVIES)
        )
    }.flow.cachedIn(viewModelScope)
}