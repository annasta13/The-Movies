package com.habileducation.themovie.useCase.fetchMovies

import com.habileducation.themovie.data.model.remote.MovieResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by Annas Surdyanto on 13/11/21.
 *
 */
interface FetchMovies {
    operator fun invoke(movieType: String): Flow<Result<MovieResponse>>
}