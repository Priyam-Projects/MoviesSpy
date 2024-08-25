package com.example.moviesspy.data.datasource.api.model

import com.example.moviesspy.domain.config.ImageConfig
import com.example.moviesspy.domain.model.Image
import com.example.moviesspy.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieApiModel(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    val id: Int,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
) {
    fun toMovie(config: ImageConfig): Movie {
        return Movie(
            title = this.title,
            overview = this.overview,
            homePageImage = Image(
                url = config.baseUrl + config.homePageSize + this.posterPath,
                movieTitle = title,
            ),
            detailsPageImage = Image(
                url = config.baseUrl + config.detailPageSize + this.posterPath,
                movieTitle = title,
            )
        )
    }
}
