package com.habileducation.themovie.di

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
import com.habileducation.themovie.useCase.insertFavorite.SetFavorite
import com.habileducation.themovie.useCase.insertFavorite.SetFavoriteImpl
import com.habileducation.themovie.viewModel.MovieDetailSharedViewModel
import com.habileducation.themovie.viewModel.MovieSharedViewModel

/**
 * Created by Annas Surdyanto on 29/11/21.
 *
 */
class MovieModule {
    private val appModule = AppModule()

    private val localSource: MovieLocalDataSource by lazy {
        MovieLocalDataSourceImpl(appDatabase = appModule.appDatabase)
    }

    private val remoteSource: MovieRemoteDataSource by lazy {
        MovieRemoteDataSourceImpl(apiService = appModule.apiService)
    }

    private val movieRepository: MovieRepository by lazy {
        MovieRepositoryImpl(localDataSource = localSource, remoteDataSource = remoteSource)
    }

    private val fetchMovies: FetchMovies by lazy {
        FetchMoviesImpl(movieRepository)
    }

    private val fetchDetail: FetchDetail by lazy {
        FetchDetailImpl(movieRepository)
    }

    private val checkFavoriteStatus: CheckFavoriteStatus by lazy {
        CheckFavoriteStatusImpl(movieRepository)
    }

    private val setFavorite: SetFavorite by lazy {
        SetFavoriteImpl(movieRepository)
    }

    val movieSharedViewModel: MovieSharedViewModel by lazy {
        MovieSharedViewModel(fetchMovies)
    }

    val movieDetailSharedViewModel: MovieDetailSharedViewModel by lazy {
        MovieDetailSharedViewModel(fetchDetail, checkFavoriteStatus, setFavorite)
    }
}