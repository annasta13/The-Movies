package com.habileducation.themovie.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.habileducation.themovie.android.R
import com.habileducation.themovie.android.ui.theme.keyLine2
import com.habileducation.themovie.android.ui.theme.keyLine3

/**
 * Created by Annas Surdyanto on 15/07/22.
 *
 */

@Composable
fun <T : Any> PagingView(
    modifier: Modifier = Modifier,
    list: LazyPagingItems<T>,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: LazyListScope.() -> Unit
) {

    if (list.loadState.refresh is Error) {
        val e = list.loadState.refresh as Error
        ErrorScreen(message = e.error.message.toString(), onRetry = list::refresh)
    } else {
        LazyColumn(modifier = modifier, contentPadding = contentPadding) {
            content()
            list.apply {
                when {
                    //manage load state when next response page is loading
                    loadState.append is Loading -> item { ListLoadingView() }

                    loadState.append is Error && loadState.append !is Loading -> {
                        val e = loadState.append as Error
                        item {
                            ListErrorView(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = keyLine2),
                                message = e.error.message.toString(),
                                onClick = list::retry
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ListLoadingView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = keyLine3),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SwipeRefreshIndicator(SwipeRefreshState(true), 50.dp)
    }
}

@Composable
fun ListErrorView(
    modifier: Modifier = Modifier,
    message: String,
    onClick: () -> Unit
) {
    ConstraintLayout(
        modifier = modifier
            .background(Color.Gray)
            .padding(horizontal = keyLine3)
            .padding(vertical = keyLine2)
    ) {
        val (textRef, buttonRef) = createRefs()
        Text(
            text = message, modifier = Modifier.constrainAs(textRef) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(buttonRef.start, margin = 4.dp)
                width = Dimension.fillToConstraints
            },
            style = MaterialTheme.typography.caption.copy(color = Color.White)
        )
        PrimaryButton(
            text = stringResource(id = R.string.retry),
            onClick = onClick,
            modifier = Modifier
                .clickable(onClick = onClick)
                .constrainAs(buttonRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
        )
    }
}

@Composable
fun ErrorDialogView(
    throwable: Throwable? = null,
    serverErrorMessage: String?,
    onRetry: () -> Unit,
    onCancel: (() -> Unit)? = null
) {
    if (throwable != null) {
        RetryDialog(
            title = stringResource(id = R.string.error_title),
            message = throwable.message.toString(),
            onConfirmed = onRetry,
            onCancel = onCancel
        )
    } else if (!serverErrorMessage.isNullOrEmpty())
        RetryDialog(
            title = stringResource(id = R.string.error_title),
            message = serverErrorMessage,
            onConfirmed = onRetry,
            onCancel = onCancel
        )
}