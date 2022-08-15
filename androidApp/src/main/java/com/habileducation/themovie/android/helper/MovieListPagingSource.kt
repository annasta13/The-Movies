package com.habileducation.themovie.android.helper

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.habileducation.themovie.data.model.local.Movie
import com.habileducation.themovie.data.model.remote.asDomainMovieList
import com.habileducation.themovie.viewModel.MovieListSharedViewModel
import kotlinx.coroutines.flow.last

/**
 * Created by Annas Surdyanto on 20/06/22.
 *
 */
class MovieListPagingSource(
    private val sharedViewModel: MovieListSharedViewModel,
    private val url: String
) :
    PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val nextPage = params.key ?: 1
        val result = sharedViewModel.loadMovie(page = params.key ?: 1, url = url).last()
        val currentPage = result.movieResponse?.page ?: 1
        val pageSize = result.movieResponse?.totalPages ?: 1
        val list = result.movieResponse?.movieList.asDomainMovieList()
        return PagingHelper.getReturn(
            throwable = result.error,
            list = list,
            nextPage = nextPage,
            pageSize = pageSize,
            currentPage = currentPage,
            serverErrorMessage = result.movieResponse?.message
        )
    }
}