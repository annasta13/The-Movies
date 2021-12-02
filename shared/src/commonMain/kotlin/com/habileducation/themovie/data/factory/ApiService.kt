package com.habileducation.themovie.data.factory

import io.ktor.client.*

/**
 * Created by Annas Surdyanto on 12/08/21.
 *
 */
expect class ApiService() {
    fun build(): HttpClient
}