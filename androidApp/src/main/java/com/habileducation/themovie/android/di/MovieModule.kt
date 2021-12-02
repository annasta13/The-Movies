package com.habileducation.themovie.android.di

import com.habileducation.movie.data.source.AppDatabase
import com.habileducation.themovie.data.repo.MovieRepository
import com.habileducation.themovie.data.repo.MovieRepositoryImpl
import com.habileducation.themovie.data.source.local.MovieLocalDataSource
import com.habileducation.themovie.data.source.local.MovieLocalDataSourceImpl
import com.habileducation.themovie.data.source.remote.MovieRemoteDataSource
import com.habileducation.themovie.data.source.remote.MovieRemoteDataSourceImpl
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
import com.habileducation.themovie.viewModel.MovieSharedViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

/**
 * Created by Annas Surdyanto on 14/11/21.
 *
 */

@Module
@InstallIn(SingletonComponent::class)
object MovieModule {

    @Singleton
    @Provides
    fun provideLocalSource(appDatabase: AppDatabase): MovieLocalDataSource {
        return MovieLocalDataSourceImpl(appDatabase)
    }

    @Singleton
    @Provides
    fun provideRemoteSource(apiService: HttpClient): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(apiService)
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
    fun provideMovieSharedViewModel(fetchMovies: FetchMovies): MovieSharedViewModel {
        return MovieSharedViewModel(fetchMovies)
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