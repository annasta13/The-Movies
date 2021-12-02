package com.habileducation.themovie.useCase

import com.habileducation.themovie.AppKey
import com.habileducation.themovie.data.model.remote.MovieResponse
import com.habileducation.themovie.domain.FakeRemoteDataSource
import com.habileducation.themovie.useCase.fetchMovies.FetchMovies
import kotlinx.coroutines.flow.Flow

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */
class FakeFetchMovies : FetchMovies {
    override fun invoke(movieType: String): Flow<Result<MovieResponse>> {
        val fakeRemoteDataSource = FakeRemoteDataSource()
        return fakeRemoteDataSource.fetchMovies(movieType)
    }
}