package com.habileducation.themovie.data

import com.habileducation.themovie.data.model.remote.MovieDetailAndReview
import com.habileducation.themovie.data.model.remote.MovieResponseDto
import com.habileducation.themovie.data.source.remote.MovieRemoteDataSource
import com.habileducation.themovie.util.FileReader
import com.habileducation.themovie.util.JsonParser

/**
 * Created by Annas Surdyanto on 23/11/21.
 *
 */
class FakeRemoteDataSource : MovieRemoteDataSource {
    private val jsonParser = JsonParser()
    private val fileReader = FileReader()
    override suspend fun loadMovie(url: String, page: Int): MovieResponseDto {
        val responseString = getMovieListResponse(url)
        return jsonParser.decodeMovieListResponse(responseString)
    }

    override suspend fun fetchMovieDetailAndReview(movieId: Long): MovieDetailAndReview {
        val movie = fileReader.readFile("movie_detail_${movieId}.json")
        val review = fileReader.readFile("reviews_${movieId}.json")
        return jsonParser.decodeMovieDetailAndReview(movie, review)
    }

    private fun getMovieListResponse(url: String): String {
        val responseFile = when (url) {
            "popular" -> "popular_movies.json"
            "top_rated" -> "top_rated.json"
            "upcoming" -> "upcoming.json"
            else -> "now_playing.json"

        }
        return fileReader.readFile(responseFile)
    }
}