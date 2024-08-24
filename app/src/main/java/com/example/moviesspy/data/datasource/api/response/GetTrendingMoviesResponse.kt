package com.example.moviesspy.data.datasource.api.response

import com.example.moviesspy.data.datasource.api.model.MovieApiModel
import com.google.gson.annotations.SerializedName

data class GetTrendingMoviesResponse(
    val page: Int,
    @SerializedName("results")
    val movies: List<MovieApiModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
)
