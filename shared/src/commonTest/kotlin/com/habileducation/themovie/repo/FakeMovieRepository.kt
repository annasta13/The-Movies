package com.habileducation.themovie.repo

import com.habileducation.themovie.data.model.local.Movie
import com.habileducation.themovie.data.model.remote.MovieDetailAndReview
import com.habileducation.themovie.data.model.remote.MovieResponse
import com.habileducation.themovie.data.repo.MovieRepository
import com.habileducation.themovie.domain.FakeLocalDataSource
import com.habileducation.themovie.domain.FakeRemoteDataSource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */
class FakeMovieRepository : MovieRepository {
    private var givenFavoriteMovie: List<Movie>? = null
    private var fakeLocalDataSource = FakeLocalDataSource(givenFavoriteMovie)

    override fun fetchMovies(movieType: String): Flow<Result<MovieResponse>> {
        val fakeRemoteDataSource = FakeRemoteDataSource()
        return fakeRemoteDataSource.fetchMovies(movieType)
    }

    override fun fetchMovieDetail(movieId: Long): Flow<Result<MovieDetailAndReview>> {
        val fakeRemoteDataSource = FakeRemoteDataSource()
        return fakeRemoteDataSource.fetchMovieDetailAndReview(movieId)
    }

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return fakeLocalDataSource.getFavoriteMovie()
    }

    override suspend fun isFavorited(movieId: Long): Boolean {
        return fakeLocalDataSource.isFavorited(movieId)
    }

    override suspend fun setFavorite(movie: Movie): Boolean {
        return fakeLocalDataSource.setFavorite(movie)
    }

    fun setGivenFavoriteMovie(given: List<Movie>) {
        givenFavoriteMovie = given
        fakeLocalDataSource = FakeLocalDataSource(givenFavoriteMovie)
    }
}