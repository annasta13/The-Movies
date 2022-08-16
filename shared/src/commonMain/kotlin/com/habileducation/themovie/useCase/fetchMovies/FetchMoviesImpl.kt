package com.habileducation.themovie.useCase.fetchMovies

import com.habileducation.themovie.data.model.local.MovieResponse
import com.habileducation.themovie.data.model.remote.MovieResponseDto
import com.habileducation.themovie.data.repo.MovieRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Annas Surdyanto on 13/11/21.
 *
 */
class FetchMoviesImpl(private val movieRepository: MovieRepository) : FetchMovies {
    override fun loadMovie(url: String, page: Int): Flow<Result<MovieResponse>> {
        return movieRepository.loadMovie(url, page)
    }
}