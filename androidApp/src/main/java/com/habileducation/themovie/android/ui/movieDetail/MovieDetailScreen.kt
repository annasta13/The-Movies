package com.habileducation.themovie.android.ui.movieDetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.habileducation.themovie.android.R
import com.habileducation.themovie.android.ui.components.ErrorScreen
import com.habileducation.themovie.android.ui.components.ScreenContainer
import com.habileducation.themovie.viewState.MovieDetailViewState

/**
 * Created by Annas Surdyanto on 18/11/21.
 *
 */
@Composable
fun MovieDetailScreen(
    movieId: Long,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value
    if (state == MovieDetailViewState.empty) viewModel.initState(movieId)
    val title = state.movieResponse?.movieDetail?.title ?: "Movie Detail"
    ScreenContainer(
        loadingState = state.isLoading,
        onRefresh = { viewModel.initState(movieId) },
        barTitle = title,
        trailingIcon = if (state.movieResponse == null) null else if (state.isFavorited) R.drawable.favorited else R.drawable.unfavorited,
        onTrailingIconClicked = viewModel::setFavorite
    ) {
        state.movieResponse?.let {
            MovieDetailScreenContent(
                data = it,
            )
        }
        state.error?.let {
            ErrorScreen(message = it.message.toString()) {
                viewModel.initState(movieId)
            }
        }
    }
}