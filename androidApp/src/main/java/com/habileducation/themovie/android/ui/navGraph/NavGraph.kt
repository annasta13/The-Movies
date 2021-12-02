package com.habileducation.themovie.android.ui.navGraph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.habileducation.themovie.android.ui.favoriteMovie.FavoriteMovieScreen
import com.habileducation.themovie.android.ui.main.MainScreen
import com.habileducation.themovie.android.ui.movieDetail.MovieDetailScreen

/**
 * Created by Annas Surdyanto on 06/09/21.
 *
 */


@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    val action = remember(navController) { AppAction(navController) }
    NavHost(navController = navController, startDestination = AppDestination.mainScreen) {
        composable(AppDestination.mainScreen) {
            MainScreen(
                onMovieClicked = action.navigateToDetail,
                onNavigateToFavorite = action.navigateToFavorite
            )
        }

        composable("${AppDestination.movieDetail}/{${AppArgument.MOVIE_ID}}") { backStackEntry->
            val movieId = backStackEntry.arguments?.getString(AppArgument.MOVIE_ID)?: ""
            print("Check movie id $movieId")
            MovieDetailScreen(movieId.toLong())
        }

        composable(AppDestination.favorite) {
            FavoriteMovieScreen(onMovieClicked = action.navigateToDetail)
        }
    }
}

/*
@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.arguments?.getString("KEY_ROUTE")
}*/
