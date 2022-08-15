package com.habileducation.themovie.android.ui.main.upComing

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.habileducation.themovie.android.ui.components.MovieItemView
import com.habileducation.themovie.android.ui.components.PagingView
import com.habileducation.themovie.android.ui.components.ScreenContainer
import com.habileducation.themovie.android.ui.theme.keyLine3

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */

@Composable
fun UpcomingScreen(
    onMovieClicked: (movieId: Long) -> Unit,
    viewModel: UpComingViewModel = hiltViewModel()
) {
    val list = viewModel.list.collectAsLazyPagingItems()
    val isLoading = list.loadState.refresh is LoadState.Loading

    ScreenContainer(
        loadingState = isLoading,
        onRefresh = list::refresh,
    ) {
        PagingView(
            list = list,
            contentPadding = PaddingValues(keyLine3)
        ) {
            items(list) { item ->
                item?.let {
                    MovieItemView(onMovieClicked, it)
                }
            }
        }
    }
}