package com.habileducation.themovie.data.model.remote

import com.habileducation.themovie.data.model.local.Movie
import com.habileducation.themovie.data.model.local.MovieResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponseDto(
    @SerialName(value = "results") val movieList: List<MovieDataDto>?,
    @SerialName(value = "page") val page: Int?,
    @SerialName(value = "status_message") val message: String?,
    @SerialName(value = "total_pages") val totalPages: Int?,
) {
    /**To help SwiftUI*/
    val movieDataList : List<Movie> get() = movieList.asDomainMovieList()
    val onCompleted get() = MovieResponse(
        movieList = movieList.asDomainMovieList(),
        page = page?:0,
        message = message,
        totalPages = totalPages?:0
    )

    companion object {
        val mocked = MovieResponseDto(listOf(MovieDataDto.mocked),1,null, 1)
    }
}

fun List<MovieDataDto>?.asDomainMovieList(): List<Movie>{
    return this?.map { it.asDomainModel() }?: emptyList()
}

@Serializable
data class MovieDataDto(
    @SerialName("id") val id: Long,
    @SerialName("original_title") val originalTitle: String,
    @SerialName("overview") val overview: String,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("release_date") val releaseDate: String,
) {
    companion object {
        val mocked = MovieDataDto(1L, "title", "overview", "path", "21-21-2022")
    }
}

/** Convert remote response to [Movie] Domain objects*/
fun MovieDataDto.asDomainModel(): Movie {
    return Movie(
        movieId = id,
        originalTitle = originalTitle,
        overview = overview,
        posterPath = posterPath ?: "",
        releaseDate = releaseDate
    )
}
