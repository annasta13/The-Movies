package com.habileducation.themovie.android.ui.main.topRated

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.habileducation.themovie.android.ui.components.ErrorScreen
import com.habileducation.themovie.android.ui.components.ScreenContainer
import com.habileducation.themovie.android.ui.main.popular.MovieContentScreen
import com.habileducation.themovie.data.model.remote.asDomainMovieList

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */

@Composable
fun TopRatedScreen(
    onMovieClicked: (movieId: Long) -> Unit,
    viewModel: TopRatedViewModel = hiltViewModel()
) {
    val state = viewModel.viewState.collectAsState().value

    ScreenContainer(
        loadingState = state.isLoading,
        onRefresh = viewModel::initState,
    ) {
        state.movieResponse?.let {
            MovieContentScreen(
                movieList = it.movieList.asDomainMovieList(),
                onMovieClicked = onMovieClicked
            )
        }
        state.error?.let {
            ErrorScreen(message = it.message.toString()) {
                viewModel.initState()
            }
        }
    }
}