package com.habileducation.themovie.useCase.fetchDetail

import com.habileducation.themovie.data.model.remote.MovieDetailAndReview
import kotlinx.coroutines.flow.Flow

/**
 * Created by Annas Surdyanto on 13/11/21.
 *
 */
interface FetchDetail {
    operator fun invoke(movieId: Long): Flow<Result<MovieDetailAndReview?>>
}