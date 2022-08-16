package com.habileducation.themovie.useCase.fetchMovies

import com.habileducation.themovie.data.model.local.MovieResponse
import com.habileducation.themovie.data.model.remote.MovieResponseDto
import kotlinx.coroutines.flow.Flow

/**
 * Created by Annas Surdyanto on 13/11/21.
 *
 */
interface FetchMovies {
    fun loadMovie(url: String, page: Int): Flow<Result<MovieResponse>>
}