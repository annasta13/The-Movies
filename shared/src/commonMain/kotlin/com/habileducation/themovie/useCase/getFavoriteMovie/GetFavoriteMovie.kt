package com.habileducation.themovie.useCase.getFavoriteMovie

import com.habileducation.themovie.data.model.local.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Created by Annas Surdyanto on 13/11/21.
 *
 */
interface GetFavoriteMovie {
    operator fun invoke(): Flow<List<Movie>>
}