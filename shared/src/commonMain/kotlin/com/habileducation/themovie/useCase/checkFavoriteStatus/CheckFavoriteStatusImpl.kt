package com.habileducation.themovie.useCase.checkFavoriteStatus

import com.habileducation.themovie.data.repo.MovieRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Annas Surdyanto on 19/11/21.
 *
 */
class CheckFavoriteStatusImpl(private val movieRepository: MovieRepository) : CheckFavoriteStatus {
    override suspend fun isFavorited(movieId: Long): Boolean {
        return movieRepository.isFavorited(movieId)
    }

}