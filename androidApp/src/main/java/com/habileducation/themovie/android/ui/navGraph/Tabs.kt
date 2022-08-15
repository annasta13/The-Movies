package com.habileducation.themovie.android.ui.navGraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.habileducation.themovie.android.ui.main.TabItem
import com.habileducation.themovie.android.ui.theme.keyLine2
import kotlinx.coroutines.launch

/**
 * Created by Annas Surdyanto on 18/11/21.
 *
 */

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = pagerState.currentPage == index,
                content = {
                    Text(
                        text = stringResource(id = tab.title),
                        modifier = Modifier.padding(keyLine2)
                    )
                },
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(
    tabs: List<TabItem>,
    pagerState: PagerState,
    onMovieClicked: (movieId: Long) -> Unit,
    padding: PaddingValues
) {
    HorizontalPager(contentPadding = padding, state = pagerState, count = tabs.size) { page ->
        tabs[page].screen(onMovieClicked = onMovieClicked)
    }
}