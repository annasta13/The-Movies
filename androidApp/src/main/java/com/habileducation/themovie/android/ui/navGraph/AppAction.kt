package com.habileducation.themovie.android.ui.navGraph

import androidx.navigation.NavController
import androidx.navigation.NavOptions

/**
 * Created by Annas Surdyanto on 10/08/21.
 *
 */

object AppDestination {
    const val mainScreen = "MAIN_SCREEN"
    const val movieDetail = "MOVIE_DETAIL"
    const val favorite = "FAVORITE"
}

object AppArgument {
    const val MOVIE_ID = "MOVIE_ID"
}

class AppAction(navController: NavController) {
    val inclusiveNavOptions = NavOptions.Builder()
        .setPopUpTo(0, true, true)
        .build()

    val navigateToDetail: (movieId: Long) -> Unit = { movieId ->
        navController.navigate(
            route = "${AppDestination.movieDetail}/$movieId"
        )
    }

    val navigateToFavorite: () -> Unit = {
        navController.navigate(route = AppDestination.favorite)
    }
}