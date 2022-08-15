package com.habileducation.themovie.data.factory

import io.ktor.client.*
import io.ktor.client.engine.ios.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.serialization.ExperimentalSerializationApi

/**
 * Created by Annas Surdyanto on 12/08/21.
 *
 */

@OptIn(ExperimentalSerializationApi::class)
actual class ApiService {
    actual fun build(): HttpClient {
        return HttpClient(Ios) {
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