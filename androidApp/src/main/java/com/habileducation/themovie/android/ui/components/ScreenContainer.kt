package com.habileducation.themovie.android.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState

/**
 * Created by Annas Surdyanto on 18/11/21.
 *
 */

@Composable
fun ScreenContainer(
    modifier: Modifier = Modifier,
    loadingState: Boolean,
    onRefresh: () -> Unit,
    appBarBackground: Color = MaterialTheme.colors.primary,
    appBarContentColor: Color = MaterialTheme.colors.onPrimary,
    barTitle: String? = null,
    leadingIcon: Int? = null,
    trailingIcon: Int? = null,
    onLeadingIconClicked: (() -> Unit)? = null,
    onTrailingIconClicked: (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            barTitle?.let { title ->
                AppBar(
                    barTitle = title,
                    appBarBackground = appBarBackground,
                    appBarContentColor = appBarContentColor,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon,
                    onLeadingIconClicked = onLeadingIconClicked,
                    onTrailingIconClicked = onTrailingIconClicked
                )
            }
        }) {
        SwipeRefresh(
            state = SwipeRefreshState(loadingState),
            onRefresh = onRefresh,
            modifier = Modifier.fillMaxSize()
        ) {
            content(it)
        }
    }
}