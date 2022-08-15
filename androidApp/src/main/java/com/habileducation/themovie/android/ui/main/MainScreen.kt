package com.habileducation.themovie.android.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.habileducation.themovie.android.R
import com.habileducation.themovie.android.ui.components.AppBar
import com.habileducation.themovie.android.ui.navGraph.Tabs
import com.habileducation.themovie.android.ui.navGraph.TabsContent

/**
 * Created by Annas Surdyanto on 18/11/21.
 *
 */

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun MainScreen(onMovieClicked: (movieId: Long) -> Unit, onNavigateToFavorite: () -> Unit) {
    val tabs = listOf(TabItem.Popular, TabItem.NowPlaying, TabItem.Upcoming, TabItem.TopRated)
    val pagerState = rememberPagerState(0)
    Scaffold(
        topBar = {
            AppBar(
                barTitle = stringResource(R.string.AppName),
                appBarBackground = MaterialTheme.colors.primary,
                appBarContentColor = MaterialTheme.colors.onPrimary,
                trailingIcon = R.drawable.favorited,
                onTrailingIconClicked = onNavigateToFavorite
            )
        }
    ) {padding->
        Column {
            Tabs(tabs = tabs, pagerState = pagerState)
            TabsContent(tabs = tabs, pagerState = pagerState, onMovieClicked = onMovieClicked, padding = padding)
        }
    }
}