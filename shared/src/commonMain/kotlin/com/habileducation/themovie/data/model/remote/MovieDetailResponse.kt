package com.habileducation.themovie.data.model.remote

import com.habileducation.themovie.data.model.local.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Annas Surdyanto on 13/11/21.
 *
 */
@Serializable
data class MovieDetailResponse(
    @SerialName("id")
    val id: Long,
    @SerialName("adult")
    val adult: Boolean,
    @SerialName("budget")
    val budget: Long,
    @SerialName("homepage")
    val homepage: String?,
    @SerialName("original_title")
    val originalTitle: String,
    @SerialName("overview")
    val overview: String?,
    @SerialName("popularity")
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("title")
    val title: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("vote_average")
    val voteAverage: Double,
) {
    val poster = "https://image.tmdb.org/t/p/w300/$posterPath"
    companion object {
        val mocked = MovieDetailResponse(
            id = 580489,
            adult = false,
            budget = 110000000,
            homepage = "https://www.venom.movie",
            originalTitle = "Venom: Let There Be Carnage",
            overview = "After finding a host body in investigative reporter Eddie Brock, the alien symbiote must face a new enemy, Carnage, the alter ego of serial killer Cletus Kasady.",
            popularity = 4497.836,
            posterPath = "/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg",
            title = "Venom: Let There Be Carnage",
            releaseDate = "2021-09-30",
            voteAverage = 6.8
        )
    }
}

fun MovieDetailResponse.asDomainMovie(): Movie {
    return Movie(
        posterPath = posterPath?:"",
        overview = overview?:"",
        releaseDate = releaseDate,
        movieId = id,
        originalTitle = originalTitle
    )
}
