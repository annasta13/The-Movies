package com.habileducation.themovie.useCase.fetchDetail

import com.habileducation.themovie.data.model.remote.MovieDetailAndReview
import com.habileducation.themovie.data.repo.MovieRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Annas Surdyanto on 13/11/21.
 *
 */
class FetchDetailImpl(private val movieRepository: MovieRepository) : FetchDetail {
    override fun invoke(movieId: Long): Flow<Result<MovieDetailAndReview?>> {
        return movieRepository.fetchMovieDetail(movieId)
    }
}