package com.habileducation.themovie.di

import com.habileducation.movie.data.source.AppDatabase
import com.habileducation.themovie.data.factory.ApiService
import com.habileducation.themovie.data.factory.AppDatabaseFactory
import com.habileducation.themovie.data.factory.DatabaseFactory
import io.ktor.client.*

/**
 * Created by Annas Surdyanto on 29/11/21.
 *
 */
class AppModule {
    val apiService: HttpClient by lazy {
        ApiService().build()
    }

    val appDatabase: AppDatabase by lazy {
        AppDatabaseFactory(databaseFactory = DatabaseFactory()).createDatabase()
    }
}