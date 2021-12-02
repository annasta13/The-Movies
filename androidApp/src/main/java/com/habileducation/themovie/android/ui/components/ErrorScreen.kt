package com.habileducation.themovie.android.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import id.co.vmk.loyal.android.theme.keyLine3

/**
 * Created by Annas Surdyanto on 16/11/21.
 *
 */

@Composable
fun ErrorScreen(
    message: String = "Something went wrong.",
    onRetry: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(keyLine3)
    ) {
        ConstraintLayout(modifier = Modifier.align(Alignment.Center)) {
            val (textRef, buttonRef) = createRefs()
            Text(
                text = message, modifier = Modifier.constrainAs(textRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(buttonRef.top, margin = keyLine3)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.caption
            )

            onRetry?.let {
                PrimaryButton(
                    text = "Retry",
                    onClick = it,
                    modifier = Modifier.constrainAs(buttonRef) {
                        top.linkTo(textRef.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )
            }
        }
    }
}

@Composable
@Preview
fun ErrorScreenPreview() = ErrorScreen {}