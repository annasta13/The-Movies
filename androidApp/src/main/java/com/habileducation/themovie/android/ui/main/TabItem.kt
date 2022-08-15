package com.habileducation.themovie.android.ui.main

import androidx.compose.runtime.Composable
import com.habileducation.themovie.android.R
import com.habileducation.themovie.android.ui.main.nowPlaying.NowPLayingScreen
import com.habileducation.themovie.android.ui.main.popular.PopularScreen
import com.habileducation.themovie.android.ui.main.topRated.TopRatedScreen
import com.habileducation.themovie.android.ui.main.upComing.UpcomingScreen

/**
 * Created by Annas Surdyanto on 18/11/21.
 *
 */
sealed class TabItem(
    var title: Int,
    var screen: @Composable (onMovieClicked: (movieId: Long) -> Unit) -> Unit
) {
    object Popular :
        TabItem(
            R.string.popular,
            { onMovieClicked -> PopularScreen(onMovieClicked = onMovieClicked) })

    object NowPlaying :
        TabItem(
            R.string.playing,
            { onMovieClicked -> NowPLayingScreen(onMovieClicked = onMovieClicked) })

    object Upcoming :
        TabItem(
            R.string.upcoming,
            { onMovieClicked -> UpcomingScreen(onMovieClicked = onMovieClicked) })

    object TopRated :
        TabItem(
            R.string.top_rated,
            { onMovieClicked -> TopRatedScreen(onMovieClicked = onMovieClicked) })
}