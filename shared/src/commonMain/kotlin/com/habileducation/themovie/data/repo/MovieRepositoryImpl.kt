package com.habileducation.themovie.data.repo

import com.habileducation.themovie.data.model.local.Movie
import com.habileducation.themovie.data.model.local.MovieResponse
import com.habileducation.themovie.data.model.remote.MovieDetailAndReview
import com.habileducation.themovie.data.source.local.MovieLocalDataSource
import com.habileducation.themovie.data.source.remote.MovieRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */
class MovieRepositoryImpl(
    private val localDataSource: MovieLocalDataSource,
    private val remoteDataSource: MovieRemoteDataSource
) : MovieRepository {
    override fun fetchMovieDetail(movieId: Long): Flow<Result<MovieDetailAndReview?>> = flow {
        emit(Result.success(remoteDataSource.fetchMovieDetailAndReview(movieId)))
    }.catch { e -> emit(Result.failure(e)) }

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie()
    }

    override suspend fun isFavorited(movieId: Long): Boolean {
        return localDataSource.isFavorited(movieId)
    }

    override suspend fun setFavorite(movie: Movie): Boolean {
        return localDataSource.setFavorite(movie)
    }

    override fun loadMovie(url: String, page: Int): Flow<Result<MovieResponse>> = flow {
        emit(Result.success(remoteDataSource.loadMovie(url, page).onCompleted))
    }.catch { e -> emit(Result.failure(e)) }
}