package com.habileducation.themovie.useCase.fetchMovies

import com.habileducation.themovie.data.model.remote.MovieResponse
import com.habileducation.themovie.data.repo.MovieRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Annas Surdyanto on 13/11/21.
 *
 */
class FetchMoviesImpl(private val movieRepository: MovieRepository) : FetchMovies {
    override fun invoke(movieType: String): Flow<Result<MovieResponse>> {
        return movieRepository.fetchMovies(movieType)
    }
}