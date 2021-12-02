package com.habileducation.themovie.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.habileducation.themovie.android.MainActivity
import com.habileducation.themovie.android.di.AppModule
import com.habileducation.themovie.android.di.MovieModule
import com.habileducation.themovie.android.ui.movieDetail.MovieDetailScreen
import com.habileducation.themovie.android.util.TestTag
import com.habileducation.themovie.data.model.local.Movie
import com.habileducation.themovie.data.model.remote.asDomainMovie
import com.habileducation.themovie.data.FakeRemoteDataSource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import id.co.vmk.loyal.android.theme.LightTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test


/**
 * Created by Annas Surdyanto on 19/11/21.
 *
 */


@UninstallModules(MovieModule::class, AppModule::class)
@HiltAndroidTest
class MovieDetailTest {
    val millis = 100L
    val movieDetailResponseMocked = FakeRemoteDataSource().fetchMovieDetailAndReview(19404)
    lateinit var movie: Movie

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule(MainActivity::class.java)

    @Before
    fun setUp() {
        runBlocking { movie = movieDetailResponseMocked.first().getOrNull()!!.movieDetail!!.asDomainMovie() }
        composeRule.setContent {
            LightTheme {
                MovieDetailScreen(movie.movieId)
            }
        }
    }

    @Test
    fun myTest() {
        composeRule.onNodeWithTag(TestTag.trailingIcon).assertIsDisplayed()
        composeRule.onNodeWithTag(TestTag.movieDetailIcon).assertIsDisplayed()
        composeRule.mainClock.advanceTimeBy(millis)
        composeRule.onNodeWithTag(TestTag.movieDetailBudget).assertIsDisplayed()
        composeRule.mainClock.advanceTimeBy(millis)
        composeRule.onNodeWithTag(TestTag.movieDetailOverview).assertIsDisplayed()
        composeRule.mainClock.advanceTimeBy(millis)
        composeRule.onNodeWithTag(TestTag.movieDetailReleaseDate).assertIsDisplayed()
        composeRule.mainClock.advanceTimeBy(millis)
        composeRule.onNodeWithTag(TestTag.movieDetailPopularity).assertIsDisplayed()
        composeRule.mainClock.advanceTimeBy(millis)
        composeRule.onNodeWithTag(TestTag.movieDetailVote).assertIsDisplayed()
        composeRule.onNodeWithTag(TestTag.trailingIcon).assertIsDisplayed()
    }
}