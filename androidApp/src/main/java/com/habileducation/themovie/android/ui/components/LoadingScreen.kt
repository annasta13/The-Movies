package com.habileducation.themovie.android.ui.components

import android.os.Build.VERSION.SDK_INT
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.habileducation.themovie.android.R

/**
 * Created by Annas Surdyanto on 26/08/21.
 *
 */

@Composable
fun LoadingScreen() {
    Surface {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            Column {
                LoadingIconScreen()
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun LoadingIconScreen(modifier: Modifier = Modifier) {
    CircularProgressIndicator(modifier = modifier)
}


@Composable
@Preview
fun LoadingScreenPreview() {
    LoadingScreen()
}