package com.example.moviesspy.data.repository

import android.util.Log
import com.example.moviesspy.common.ApiResult
import com.example.moviesspy.common.Constants
import com.example.moviesspy.data.datasource.api.service.MoviesService
import com.example.moviesspy.domain.config.ImageConfig
import com.example.moviesspy.domain.model.Movie
import com.example.moviesspy.domain.repository.MoviesRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesService: MoviesService,
): MoviesRepository {
    private val TAG = "MoviesRepositoryImpl"

    override suspend fun getTrendingMovies(config: ImageConfig): ApiResult<List<Movie>> {
        try {
            val response = moviesService.getTrendingMovies(
                language = Constants.LANGUAGE_API_QUERY_VALUE,
                apiKey = Constants.API_KEY,
            )
            val movies = response.movies.map {
                it.toMovie(config)
            }
            return when {
                movies.isEmpty() -> ApiResult.Error("No movies found")
                else -> ApiResult.Success(movies)
            }
        } catch (e: HttpException) {
            Log.e(TAG, "HttpException in getTrendingMovies $e")
            return ApiResult.Error("Failure while fetching movies")
        } catch (e: IOException) {
            Log.e(TAG, "IOException in getTrendingMovies $e")
            return ApiResult.Error("Please check your internet connection")
        } catch (e: Exception) {
            Log.e(TAG, "Exception in getTrendingMovies $e")
            return ApiResult.Error("Unknown error occurred")
        }
    }

    override suspend fun getImageConfig(): ApiResult<ImageConfig> {
        try {
            val response = moviesService.getImageConfig(
                apiKey = Constants.API_KEY,
            )
            val config = response.imageConfigApiModel.toImageConfig()
            return ApiResult.Success(config)
        } catch (e: HttpException) {
            Log.e(TAG, "HttpException while fetching config $e")
            return ApiResult.Error("Config fetch failed")
        } catch (e: Exception) {
            Log.e(TAG, "Exception while fetching config $e")
            return ApiResult.Error("Unknown error occurred")
        }
    }
}
