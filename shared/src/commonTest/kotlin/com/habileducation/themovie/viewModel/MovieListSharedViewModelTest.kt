package com.habileducation.themovie.viewModel

import com.habileducation.themovie.AppKey
import com.habileducation.themovie.data.repo.MovieRepositoryImpl
import com.habileducation.themovie.data.local.FakeLocalDataSource
import com.habileducation.themovie.data.remote.FakeRemoteDataSource
import com.habileducation.themovie.useCase.fetchMovies.FetchMoviesImpl
import com.habileducation.themovie.util.runBlocking
import kotlinx.coroutines.flow.last
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */
class MovieListSharedViewModelTest {
    private val fakeRemoteDataSource = FakeRemoteDataSource()
    private val fakeLocalDataSource = FakeLocalDataSource()
    private val repository = MovieRepositoryImpl(fakeLocalDataSource, fakeRemoteDataSource)
    private val fetchMovies = FetchMoviesImpl(repository)
    private val sharedViewModel = MovieListSharedViewModel(fetchMovies)

    @Test
    fun fetchMovieShouldReturnSuccess() = runBlocking {
        fakeRemoteDataSource.setMovieListResponse("popular_movies.json")
        val actualState = sharedViewModel.loadMovie(AppKey.POPULAR_MOVIES,1).last()
        assertNotNull(actualState.movieResponse)
        assertNull(actualState.error)
    }

    @Test
    fun fetchMovieShouldReturnError() = runBlocking {
        fakeRemoteDataSource.setMovieListResponse("")
        val actualState = sharedViewModel.loadMovie(AppKey.POPULAR_MOVIES,1).last()
        assertNull(actualState.movieResponse)
        assertNotNull(actualState.error)
    }
}