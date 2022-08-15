package com.habileducation.themovie.android.helper

import androidx.paging.LoadState
import androidx.paging.PagingSource.LoadResult
import androidx.paging.compose.LazyPagingItems
import timber.log.Timber
import java.io.IOException

/**
 * Created by Annas Surdyanto on 20/06/22.
 *
 */
data class EmptyDataException(
    override val message: String
) : Exception()


fun <T : Any> LazyPagingItems<T>.shouldInit(): Boolean {
    Timber.d("check should init ${this.loadState.refresh !is LoadState.Loading && this.loadState.refresh !is LoadState.Error && this.itemCount == 0}")
    return this.loadState.refresh is LoadState.NotLoading && this.loadState.prepend is LoadState.NotLoading && this.loadState.refresh !is LoadState.Error && this.itemCount == 0
}

class PagingHelper {
    companion object {
        fun <T : Any> getReturn(
            throwable: Throwable?,
            list: List<T>,
            serverErrorMessage: String?,
            nextPage: Int,
            pageSize: Int,
            currentPage: Int
        ): LoadResult<Int, T> {
            return if (throwable == null) {
                val prevKey = if (nextPage == 1) null else nextPage - 1
                val nextKey = if (pageSize == currentPage) null else currentPage + 1
                if (list.isNotEmpty()) LoadResult.Page(
                    data = list,
                    prevKey = prevKey,
                    nextKey = nextKey
                )

                //handle catched error by returning server message
                else if (!serverErrorMessage.isNullOrEmpty())
                    LoadResult.Error(EmptyDataException(serverErrorMessage))

                //handle unpredictable error
                else LoadResult.Error(Exception("Something went wrong"))

                //handle catched error by returning custom error message
            } else run {
                Timber.e("check error ${throwable.message}")
                LoadResult.Error(IOException(throwable.message.toString()))
            }
        }
    }
}