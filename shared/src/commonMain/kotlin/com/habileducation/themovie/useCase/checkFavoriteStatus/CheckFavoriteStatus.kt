package com.habileducation.themovie.useCase.checkFavoriteStatus

import kotlinx.coroutines.flow.Flow

/**
 * Created by Annas Surdyanto on 19/11/21.
 *
 */
interface CheckFavoriteStatus {
    suspend fun isFavorited(movieId: Long): Boolean
}