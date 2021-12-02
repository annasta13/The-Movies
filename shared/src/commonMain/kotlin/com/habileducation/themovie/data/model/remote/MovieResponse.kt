package com.habileducation.themovie.data.model.remote

import com.habileducation.themovie.data.model.local.LoadingProgress
import com.habileducation.themovie.data.model.local.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Annas Surdyanto on 13/11/21.
 *
 */

data class MovieRemoteData(
    val progress: LoadingProgress?,
    val response: MovieResponse?
) {
    companion object {
        val mocked = MovieRemoteData(null, MovieResponse.mocked)
    }
}

@Serializable
data class MovieResponse(
    @SerialName(value = "results") val movieList: List<MovieData> = emptyList()
) {
    /**To help SwiftUI*/
    val movieDataList : List<Movie> get() = movieList.asDomainMovieList()

    companion object {
        val mocked = MovieResponse(listOf(MovieData.mocked))
    }
}

fun List<MovieData>.asDomainMovieList(): List<Movie>{
    return map { it.asDomainModel() }
}

@Serializable
data class MovieData(
    @SerialName("id") val id: Long,
    @SerialName("original_title") val originalTitle: String,
    @SerialName("overview") val overview: String,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("release_date") val releaseDate: String,
) {
    companion object {
        val mocked = MovieData(1L, "title", "overview", "path", "21-21-2022")
    }
}

/** Convert remote response to [Model] Domain objects*/
fun MovieData.asDomainModel(): Movie {
    return Movie(
        movieId = id,
        originalTitle = originalTitle,
        overview = overview,
        posterPath = posterPath ?: "",
        releaseDate = releaseDate
    )
}
