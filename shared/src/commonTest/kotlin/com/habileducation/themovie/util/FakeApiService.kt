package com.habileducation.themovie.util

import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.serialization.ExperimentalSerializationApi


/*internal fun buildOkHttpClient(): HttpClient {

    return OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()
}*/

@OptIn(ExperimentalSerializationApi::class)
class FakeApiService() {
    fun buildApiService(movieType: String, respondFileName: String): HttpClient {
        val response = FileReader().readFile(respondFileName)
        val mockEngine = MockEngine {
            if (respondFileName != "") {
                respond(
                    content = ByteReadChannel(response),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            } else respond(content = "", status = HttpStatusCode.BadGateway)
        }
        return HttpClient(mockEngine) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true // if the server sends extra fields, ignore them
                        explicitNulls = false
                    }
                )
            }
        }
    }
}