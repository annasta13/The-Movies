package com.habileducation.themovie.viewModel

import com.habileducation.themovie.AppKey
import com.habileducation.themovie.data.model.remote.MovieResponse
import com.habileducation.themovie.util.runBlocking
import com.habileducation.themovie.viewState.MovieViewState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */
class MovieSharedViewModelTest {
    private val fakePopularMovieViewModel = FakePopularMovieViewModel()

    @Test
    fun fetchMovieShouldReturnLoading() = runBlocking {
        val state = MovieViewState.empty
        val actualState = fakePopularMovieViewModel.fetchMovie(state, AppKey.POPULAR_MOVIES).first()
        val isLoading = actualState.movieResponse == null && actualState.error == null
        assertTrue(isLoading)
    }

    @Test
    fun fetchMovieShouldReturnSuccess() = runBlocking {
        val state = MovieViewState.empty
        val actualState = fakePopularMovieViewModel.fetchMovie(state, AppKey.POPULAR_MOVIES).last()
        assertNotNull(actualState.movieResponse)
        assertNull(actualState.error)
    }

    @Test
    fun fetchMovieShouldReturnError() = runBlocking {
        val state = MovieViewState.empty
        val actualState = fakePopularMovieViewModel.fetchMovie(state, "").last()
        assertNull(actualState.movieResponse)
        assertNotNull(actualState.error)
    }
}