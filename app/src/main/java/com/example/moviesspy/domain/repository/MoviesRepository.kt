package com.example.moviesspy.domain.repository

import com.example.moviesspy.common.ApiResult
import com.example.moviesspy.domain.config.ImageConfig
import com.example.moviesspy.domain.model.Movie

interface MoviesRepository {

    suspend fun getTrendingMovies(config: ImageConfig): ApiResult<List<Movie>>

    suspend fun getImageConfig(): ApiResult<ImageConfig>
}