package com.habileducation.themovie.repo

import com.habileducation.themovie.AppKey
import com.habileducation.themovie.data.model.local.Movie
import com.habileducation.themovie.data.model.remote.*
import com.habileducation.themovie.util.*
import kotlinx.coroutines.flow.first
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */
class MovieRepositoryTest {
    private lateinit var fakeRepo: FakeMovieRepository

    @BeforeTest
    fun initRepo() {
        fakeRepo = FakeMovieRepository()
    }

    @Test
    fun fetchPopularMovieShouldReturnSuccess() = runBlocking {
        val actual = fakeRepo.fetchMovies(AppKey.POPULAR_MOVIES).first()
        val expected = popularMovies.asMovieResponse()
        assertEquals(expected = expected, actual = actual.getOrNull()!!)
    }

    @Test
    fun fetchUpcomingMovieShouldReturnSuccess() = runBlocking {
        val actual = fakeRepo.fetchMovies(AppKey.UPCOMING_MOVIES).first()
        val expected = upcomingMovies.asMovieResponse()
        assertEquals(expected = expected, actual = actual.getOrNull()!!)
    }

    @Test
    fun fetchNowPlayingMovieShouldReturnSuccess() = runBlocking {
        val actual = fakeRepo.fetchMovies(AppKey.NOW_PLAYING_MOVIES).first()
        val expected = nowPlayingMovies.asMovieResponse()
        assertEquals(expected = expected, actual = actual.getOrNull()!!)
    }

    @Test
    fun fetchTopRatedMovieShouldReturnSuccess() = runBlocking {
        val actual = fakeRepo.fetchMovies(AppKey.TOP_RATED_MOVIES).first()
        val expected = topRatedMovies.asMovieResponse()
        assertEquals(expected = expected, actual = actual.getOrNull()!!)
    }

    @Test
    fun fetchDetailMovieShouldReturnSuccess() = runBlocking {
        val expected = movieDetail.asMovieDetailResponse()
        val actual = fakeRepo.fetchMovieDetail(movieId = expected.id).first()
        assertEquals(
            expected = expected,
            actual = actual.getOrNull()!!.movieDetail
        )
    }


    @Test
    fun getMovieShouldReturnSuccess() = runBlocking {
        val given = listOf(MovieDetailResponse.mocked.asDomainMovie())
        fakeRepo.setGivenFavoriteMovie(given)
        val actual = fakeRepo.getFavoriteMovie().first()
        assertEquals(expected = given, actual = actual)
    }

    @Test
    fun getFavoriteMovieShouldReturnSuccess() = runBlocking {
        val given = listOf(Movie.mocked)
        fakeRepo.setGivenFavoriteMovie(given)
        val actual = fakeRepo.getFavoriteMovie().first()
        assertEquals(expected = given, actual = actual)
    }
}