package com.habileducation.themovie.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.habileducation.themovie.android.MainActivity
import com.habileducation.themovie.android.di.AppModule
import com.habileducation.themovie.android.di.MovieModule
import com.habileducation.themovie.android.ui.movieDetail.MovieDetailViewModel
import com.habileducation.themovie.android.ui.navGraph.NavGraph
import com.habileducation.themovie.android.util.TestTag
import com.habileducation.themovie.data.model.local.Movie
import com.habileducation.themovie.data.model.remote.asDomainMovie
import com.habileducation.themovie.data.FakeRemoteDataSource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import id.co.vmk.loyal.android.theme.AppTheme
import id.co.vmk.loyal.android.theme.LightTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

/**
 * Created by Annas Surdyanto on 19/11/21.
 *
 */

@UninstallModules(MovieModule::class, AppModule::class)
@HiltAndroidTest
class MainScreenTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)
    val movieDetailResponseMocked = FakeRemoteDataSource().fetchMovieDetailAndReview(19404)
    lateinit var movie: Movie

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    val millis = 10000L

    @ExperimentalAnimationApi
    @Before
    fun setUp() {
        runBlocking {
            movie = movieDetailResponseMocked.first().getOrNull()!!.movieDetail!!.asDomainMovie()
        }
        setContent()
    }

    private fun setContent() {
        composeRule.setContent {
            AppTheme {
                NavGraph()
            }
        }
    }

    @Test
    fun testMainScreen() {
        composeRule.onNodeWithText("The Movies").assertIsDisplayed()
        composeRule.onNodeWithText("Popular").assertIsDisplayed()
        composeRule.onNodeWithTag(TestTag.trailingIcon).assertIsDisplayed()

        composeRule.onNodeWithText("Upcoming").performClick()
        composeRule.onNodeWithText("Upcoming").assertIsDisplayed()

        composeRule.onNodeWithText("Playing").performClick()
        composeRule.onNodeWithText("Playing").assertIsDisplayed()

        composeRule.onNodeWithText("Top Rated").performClick()

        composeRule.onNodeWithText("Top Rated").assertIsDisplayed()


        /** Detail Screen */

        composeRule.onNodeWithText(movie.originalTitle).performClick()

        composeRule.onNodeWithTag(TestTag.movieDetailIcon).assertIsDisplayed()

        composeRule.onNodeWithTag(TestTag.movieDetailBudget).assertIsDisplayed()

        composeRule.onNodeWithTag(TestTag.movieDetailOverview).assertIsDisplayed()

        composeRule.onNodeWithTag(TestTag.movieDetailReleaseDate).assertIsDisplayed()

        composeRule.onNodeWithTag(TestTag.movieDetailPopularity).assertIsDisplayed()

        composeRule.onNodeWithTag(TestTag.movieDetailVote).assertIsDisplayed()

        composeRule.onNodeWithTag(TestTag.trailingIcon).performClick()


        /** Back to main screen */
        setOnBackPress()

        /** Favourite Movie Screen*/

        composeRule.onNodeWithTag(TestTag.trailingIcon).performClick()

        composeRule.onNodeWithText("Favourite Movie", ignoreCase = true).assertIsDisplayed()

        /** Back to main screen */
        setOnBackPress()

        /** Exit */
        setOnBackPress()
    }

    private fun setOnBackPress() {
        composeRule.runOnUiThread {
            composeRule.activity.onBackPressed()
        }
    }
}