package com.habileducation.themovie.useCase.getFavoriteMovie

import com.habileducation.themovie.data.model.local.Movie
import com.habileducation.themovie.data.repo.MovieRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Annas Surdyanto on 13/11/21.
 *
 */
class GetFavoriteMovieImpl(private val movieRepository: MovieRepository) : GetFavoriteMovie {
    override fun invoke(): Flow<List<Movie>> {
        return movieRepository.getFavoriteMovie()
    }
}