package com.example.moviesspy.data.datasource.api.service

import com.example.moviesspy.data.datasource.api.response.GetImageConfigResponse
import com.example.moviesspy.data.datasource.api.response.GetTrendingMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("trending/movie/week")
    suspend fun getTrendingMovies(
        @Query("language") language: String,
        @Query("api_key") apiKey: String,
    ): GetTrendingMoviesResponse

    @GET("configuration")
    suspend fun getImageConfig(
        @Query("api_key") apiKey: String,
    ): GetImageConfigResponse
}
