package com.habileducation.themovie.android.di

import android.content.Context
import com.habileducation.movie.data.source.AppDatabase
import com.habileducation.themovie.App
import com.habileducation.themovie.data.factory.ApiService
import com.habileducation.themovie.data.factory.AppDatabaseFactory
import com.habileducation.themovie.data.factory.DatabaseFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/**
 * Created by Annas Surdyanto on 13/11/21.
 *
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): App {
        return app as App
    }

    @Singleton
    @Provides
    fun provideApiService(): HttpClient {
        return ApiService().build()
    }

    @Singleton
    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Singleton
    @Provides
    fun provideDatabase(context: App): AppDatabase {
        return AppDatabaseFactory(databaseFactory = DatabaseFactory(context)).createDatabase()
    }

}