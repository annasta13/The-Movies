package com.habileducation.themovie.android.ui.favoriteMovie

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.habileducation.themovie.android.R
import com.habileducation.themovie.android.ui.components.ErrorScreen
import com.habileducation.themovie.android.ui.components.MovieItemView
import com.habileducation.themovie.android.ui.components.ScreenContainer
import com.habileducation.themovie.android.ui.theme.keyLine3

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */

@Composable
fun FavoriteMovieScreen(
    onMovieClicked: (movieId: Long) -> Unit,
    viewModel: FavoriteMovieViewModel = hiltViewModel()
) {
    val state = viewModel.viewState.collectAsState().value

    ScreenContainer(
        loadingState = state.isLoading,
        onRefresh = viewModel::initState,
        barTitle = stringResource(R.string.favourite_movie)
    ) {
        if (state.movieResponse.isNotEmpty()) {
            LazyColumn(contentPadding = PaddingValues(keyLine3)) {
                items(state.movieResponse) { movie ->
                    MovieItemView(onMovieClicked = onMovieClicked, movie = movie)
                }
            }
        } else {
            ErrorScreen(message = "No favourite movie yet.")
        }
    }
}