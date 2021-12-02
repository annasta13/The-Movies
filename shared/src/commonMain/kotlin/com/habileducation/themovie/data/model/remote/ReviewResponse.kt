package com.habileducation.themovie.data.model.remote

import com.habileducation.themovie.data.model.local.Review
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Annas Surdyanto on 13/11/21.
 *
 */
@Serializable
data class ReviewResponse(
    @SerialName(value = "results") val reviewData: List<ReviewData>
) {
    companion object {
        val mocked = ReviewResponse(reviewData = listOf(ReviewData.mocked))
    }
}

@Serializable
data class ReviewData(
    @SerialName("id") val id: String,
    @SerialName("author") val author: String,
    @SerialName("content") val content: String,
    @SerialName("author_details") val authorDetails: Avatar,
) {
    companion object {
        val mocked = ReviewData(
            id = "6123ce44a80236004580f8de",
            author = "garethmb",
            content = "The latest film in the Marvel Cinematic Universe has arrived with Shang Chi",
            authorDetails = Avatar.mocked
        )
    }
}

@Serializable
data class Avatar(
    @SerialName("avatar_path") val avatarPath: String? = null,
    @SerialName("rating") val rating: Double? = null
) {

    val avatar = "https://image.tmdb.org/t/p/w300/$avatarPath"
    companion object {
        val mocked = Avatar(
            avatarPath = "https://secure.gravatar.com/avatar/992eef352126a53d7e141bf9e8707576.jpg",
            rating = 8.0
        )
    }
}

fun ReviewData.asDomainModel(movieId: Long): Review {
    return Review(
        id = id,
        author = author,
        content = content,
        avatarPath = authorDetails.avatarPath ?: "",
        rating = authorDetails.rating ?: 0.0,
        movieId = movieId
    )
}