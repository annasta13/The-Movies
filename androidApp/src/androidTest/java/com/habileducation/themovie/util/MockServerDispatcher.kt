package com.habileducation.themovie.util
/*

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

*/
/**
 * Return ok response from mock server
 *//*

class SuccessDispatcher : Dispatcher() {

    private val responseBuilder = JsonResponseBuilder()

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "popular" -> MockResponse().setResponseCode(200)
                .setBody(
                    responseBuilder.getJsonContent(
                        fileName = "popular_movies.json"
                    )
                )
            "top_rated" -> MockResponse().setResponseCode(200)
                .setBody(
                    responseBuilder.getJsonContent(
                        fileName = "top_rated.json"
                    )
                )
            "upcoming" -> MockResponse().setResponseCode(200)
                .setBody(
                    responseBuilder.getJsonContent(
                        fileName = "upcoming.json"
                    )
                )
            "now_playing" -> MockResponse().setResponseCode(200)
                .setBody(
                    responseBuilder.getJsonContent(
                        fileName = "now_playing,json"
                    )
                )
            "19404/reviews"-> MockResponse().setResponseCode(200)
                .setBody(
                    responseBuilder.getJsonContent(
                        fileName = "reviews_19404.json"
                    )
                )
            "724089/reviews"-> MockResponse().setResponseCode(200)
                .setBody(
                    responseBuilder.getJsonContent(
                        fileName = "reviews_19404.json"
                    )
                )
            "movie/19404"-> MockResponse().setResponseCode(200)
                .setBody(
                    responseBuilder.getJsonContent(
                        fileName = "movie_detail_19404.json"
                    )
                )
            "movie/724089"-> MockResponse().setResponseCode(200)
                .setBody(
                    responseBuilder.getJsonContent(
                        fileName = "movie_detail_724089.json"
                    )
                )
            else -> MockResponse().setResponseCode(400)
        }
    }
}

private val errorResponse = MockResponse().setResponseCode(400)

*/
/**
 * Return error response from mock server
 *//*

class ErrorDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest) = errorResponse
}
*/


