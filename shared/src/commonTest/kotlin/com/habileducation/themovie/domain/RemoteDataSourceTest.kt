package com.habileducation.themovie.domain

import com.habileducation.themovie.AppKey
import com.habileducation.themovie.util.*
import kotlinx.coroutines.flow.first
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */
class RemoteDataSourceTest {
    private val fakeRemoteDataSource = FakeRemoteDataSource()

    @Test
    fun fetchMovieShouldReturnFailure() = runBlocking {

        //when
        val actual = fakeRemoteDataSource.fetchMovies("").first()

        //expected null
        assertEquals(actual.getOrNull(), null)
    }

    @Test
    fun fetchMovieShouldReturnSuccess() = runBlocking {
        //when
        val actual = fakeRemoteDataSource.fetchMovies(AppKey.POPULAR_MOVIES).first()

        //expected
        val expected = popularMovies.asMovieResponse()

        assertEquals(actual.getOrNull(), expected)
    }

    @Test
    fun fetchMovieDetailShouldReturnFailure() = runBlocking {
        //when
        val actual = fakeRemoteDataSource.fetchMovieDetailAndReview(movieId = 0L).first()

        //expected null
        assertEquals(actual.getOrNull(), null)
    }

    @Test
    fun fetchMovieDetailShouldReturnSuccess() = runBlocking {
        //when
        val expectedDetail = movieDetail.asMovieDetailResponse()
        val expectedReview = movieReview.asMovieReviewResponse()
        val actual =
            fakeRemoteDataSource.fetchMovieDetailAndReview(movieId = expectedDetail.id).first()

        assertEquals(expected = expectedDetail, actual.getOrNull()!!.movieDetail)
        assertEquals(expected = expectedReview, actual.getOrNull()!!.review)
    }
}