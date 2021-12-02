package com.habileducation.themovie.android.ui.main

import androidx.compose.runtime.Composable
import com.habileducation.themovie.android.ui.main.nowPlaying.NowPLayingScreen
import com.habileducation.themovie.android.ui.main.popular.PopularScreen
import com.habileducation.themovie.android.ui.main.topRated.TopRatedScreen
import com.habileducation.themovie.android.ui.main.upComing.UpcomingScreen

/**
 * Created by Annas Surdyanto on 18/11/21.
 *
 */
sealed class TabItem(
    var title: String,
    var screen: @Composable (onMovieClicked: (movieId: Long) -> Unit) -> Unit
) {
    object Popular :
        TabItem("Popular", { onMovieClicked -> PopularScreen(onMovieClicked = onMovieClicked) })

    object NowPlaying :
        TabItem("Playing", { onMovieClicked -> NowPLayingScreen(onMovieClicked = onMovieClicked) })

    object Upcoming :
        TabItem("Upcoming", { onMovieClicked -> UpcomingScreen(onMovieClicked = onMovieClicked) })

    object TopRated :
        TabItem("Top Rated", { onMovieClicked -> TopRatedScreen(onMovieClicked = onMovieClicked) })
}