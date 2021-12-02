package com.habileducation.themovie.useCase.insertFavorite

import com.habileducation.themovie.data.model.local.Movie
import com.habileducation.themovie.data.repo.MovieRepository

/**
 * Created by Annas Surdyanto on 13/11/21.
 *
 */
class SetFavoriteImpl(private val movieRepository: MovieRepository): SetFavorite {
    override suspend fun invoke(movie: Movie): Boolean {
        return movieRepository.setFavorite(movie)
    }
}