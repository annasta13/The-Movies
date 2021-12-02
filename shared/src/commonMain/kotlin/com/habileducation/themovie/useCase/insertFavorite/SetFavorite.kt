package com.habileducation.themovie.useCase.insertFavorite

import com.habileducation.themovie.data.model.local.Movie

/**
 * Created by Annas Surdyanto on 13/11/21.
 *
 */
interface SetFavorite {
    operator suspend fun invoke(movie: Movie): Boolean
}