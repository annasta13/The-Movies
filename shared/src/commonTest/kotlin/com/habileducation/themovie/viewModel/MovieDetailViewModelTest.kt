package com.habileducation.themovie.viewModel

import com.habileducation.themovie.data.repo.MovieRepositoryImpl
import com.habileducation.themovie.data.local.FakeLocalDataSource
import com.habileducation.themovie.data.model.remote.asDomainMovie
import com.habileducation.themovie.data.remote.FakeRemoteDataSource
import com.habileducation.themovie.useCase.checkFavoriteStatus.CheckFavoriteStatusImpl
import com.habileducation.themovie.useCase.fetchDetail.FetchDetailImpl
import com.habileducation.themovie.useCase.insertFavorite.SetFavoriteImpl
import com.habileducation.themovie.util.asCommonFlow
import com.habileducation.themovie.util.runBlocking
import com.habileducation.themovie.viewState.MovieDetailViewState
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */
class MovieDetailViewModelTest {
    private val fakeRemoteDataSource = FakeRemoteDataSource()
    private val fakeLocalDataSource = FakeLocalDataSource()
    private val repository = MovieRepositoryImpl(fakeLocalDataSource, fakeRemoteDataSource)
    private val fetchDetail = FetchDetailImpl(repository)
    private val checkFavoriteStatus = CheckFavoriteStatusImpl(repository)
    private val setFavorite = SetFavoriteImpl(repository)
    private val sharedViewModel = MovieDetailSharedViewModel(fetchDetail, checkFavoriteStatus, setFavorite)

    @Test
    fun fetchDetail() = runBlocking{
        val initialState = MovieDetailViewState.empty
        fakeRemoteDataSource.setMovieDetailResponse("movie_detail_19404.json")
        fakeRemoteDataSource.setReviewResponse("reviews_19404.json")
        val result = sharedViewModel.fetchDetail(initialState, 19404L).last()
        assertTrue(!result.isFavorited)
        assertNotNull(result.movieResponse){response->
            assertNotNull(response.movieDetail) {movie->
                assertTrue(movie.id == 19404L)
                val favouriteResult = sharedViewModel.setFavorite(result).last()
                assertTrue(favouriteResult.isFavorited)
            }
            assertNotNull(response.review){reviewResponse ->
                assertTrue(reviewResponse.reviewData.isNotEmpty())
            }
        }

    }
}