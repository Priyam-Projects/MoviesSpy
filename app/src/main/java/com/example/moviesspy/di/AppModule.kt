package com.example.moviesspy.di

import com.example.moviesspy.common.Constants
import com.example.moviesspy.data.datasource.api.service.MoviesService
import com.example.moviesspy.data.repository.MoviesRepositoryImpl
import com.example.moviesspy.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoviesService(): MoviesService {
        return Retrofit.Builder()
            .baseUrl(Constants.TMDB_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesService::class.java)
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(moviesService: MoviesService): MoviesRepository {
        return MoviesRepositoryImpl(moviesService)
    }
}