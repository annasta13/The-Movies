package com.habileducation.themovie.ui

import android.content.Context
import com.habileducation.movie.data.source.AppDatabase
import com.habileducation.themovie.android.di.AppModule
import com.habileducation.themovie.android.di.MovieModule
import com.habileducation.themovie.data.FakeRemoteDataSource
import com.habileducation.themovie.data.factory.ApiService
import com.habileducation.themovie.data.factory.AppDatabaseFactory
import com.habileducation.themovie.data.factory.DatabaseFactory
import com.habileducation.themovie.data.repo.MovieRepository
import com.habileducation.themovie.data.repo.MovieRepositoryImpl
import com.habileducation.themovie.data.source.local.MovieLocalDataSource
import com.habileducation.themovie.data.source.local.MovieLocalDataSourceImpl
import com.habileducation.themovie.data.source.remote.MovieRemoteDataSource
import com.habileducation.themovie.useCase.checkFavoriteStatus.CheckFavoriteStatus
import com.habileducation.themovie.useCase.checkFavoriteStatus.CheckFavoriteStatusImpl
import com.habileducation.themovie.useCase.fetchDetail.FetchDetail
import com.habileducation.themovie.useCase.fetchDetail.FetchDetailImpl
import com.habileducation.themovie.useCase.fetchMovies.FetchMovies
import com.habileducation.themovie.useCase.fetchMovies.FetchMoviesImpl
import com.habileducation.themovie.useCase.getFavoriteMovie.GetFavoriteMovie
import com.habileducation.themovie.useCase.getFavoriteMovie.GetFavoriteMovieImpl
import com.habileducation.themovie.useCase.insertFavorite.SetFavorite
import com.habileducation.themovie.useCase.insertFavorite.SetFavoriteImpl
import com.habileducation.themovie.viewModel.FavoriteMovieSharedViewModel
import com.habileducation.themovie.viewModel.MovieDetailSharedViewModel
import com.habileducation.themovie.viewModel.MovieListSharedViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.ktor.client.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/**
 * Created by Annas Surdyanto on 21/11/21.
 *
 */


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [MovieModule::class, AppModule::class]
)
object TestModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): HiltTestApplication {
        return app as HiltTestApplication
    }

    @Singleton
    @Provides
    fun provideApiService(): HttpClient {
        return ApiService().build()
    }

    @Singleton
    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    @Singleton
    @Provides
    fun provideDatabase(context: HiltTestApplication): AppDatabase {
        return AppDatabaseFactory(databaseFactory = DatabaseFactory(context)).createDatabase()
    }

    /**
     * Real Data Use the following data sources
     * */
    @Singleton
    @Provides
    fun provideLocalSource(appDatabase: AppDatabase): MovieLocalDataSource {
        return MovieLocalDataSourceImpl(appDatabase = appDatabase)
    }

   /* @Singleton
    @Provides
    fun provideRemoteSource(apiService: HttpClient): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(apiService)
    }*/

    /**
     * Fake Data Use the following data sources
     * */
    /*@Singleton
    @Provides
    fun provideLocalSource(): MovieLocalDataSource {
        return FakeLocalDataSource()
    }*/

    @Singleton
    @Provides
    fun provideRemoteSource(): MovieRemoteDataSource {
        return FakeRemoteDataSource()
    }

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieLocalDataSource: MovieLocalDataSource,
        movieRemoteDataSource: MovieRemoteDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(movieLocalDataSource, movieRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideFetchMovie(movieRepository: MovieRepository): FetchMovies {
        return FetchMoviesImpl(movieRepository)
    }

    @Singleton
    @Provides
    fun provideFetchMovieDetail(movieRepository: MovieRepository): FetchDetail {
        return FetchDetailImpl(movieRepository)
    }

    @Singleton
    @Provides
    fun provideCheckFavorite(movieRepository: MovieRepository): CheckFavoriteStatus {
        return CheckFavoriteStatusImpl(movieRepository)
    }

    @Singleton
    @Provides
    fun provideSetFavorite(movieRepository: MovieRepository): SetFavorite {
        return SetFavoriteImpl(movieRepository)
    }

    @Singleton
    @Provides
    fun provideMovieListSharedViewModel(fetchMovies: FetchMovies): MovieListSharedViewModel {
        return MovieListSharedViewModel(fetchMovies)
    }

    @Singleton
    @Provides
    fun provideGetFavorite(movieRepository: MovieRepository): GetFavoriteMovie {
        return GetFavoriteMovieImpl(movieRepository = movieRepository)
    }

    @Singleton
    @Provides
    fun provideFavoriteMovieSharedViewModel(getFavoriteMovie: GetFavoriteMovie): FavoriteMovieSharedViewModel {
        return FavoriteMovieSharedViewModel(getFavoriteMovie)
    }

    @Singleton
    @Provides
    fun provideMovieDetailSharedViewModel(
        fetchDetail: FetchDetail,
        checkFavoriteStatus: CheckFavoriteStatus,
        setFavorite: SetFavorite
    ): MovieDetailSharedViewModel {
        return MovieDetailSharedViewModel(fetchDetail, checkFavoriteStatus, setFavorite)
    }
}
