package com.example.moviesspy.data.datasource.api.model

import com.example.moviesspy.common.ensureHttpsUrl
import com.example.moviesspy.domain.config.ImageConfig
import com.google.gson.annotations.SerializedName

data class ImageConfigApiModel(
    @SerializedName("backdrop_sizes")
    val backdropSizes: List<String>,
    @SerializedName("base_url")
    val baseUrl: String,
    @SerializedName("logo_sizes")
    val logoSizes: List<String>,
    @SerializedName("poster_sizes")
    val posterSizes: List<String>,
    @SerializedName("profile_sizes")
    val profileSizes: List<String>,
    @SerializedName("secure_base_url")
    val secureBaseUrl: String,
    @SerializedName("still_sizes")
    val stillSizes: List<String>,
) {
    // todo use a strategy based pattern to select the home and detail page size
    fun toImageConfig(): ImageConfig {
        return ImageConfig(
            baseUrl = this.baseUrl.ensureHttpsUrl(),
            // handle it, currently config has more than 4 no of sizes
            homePageSize = this.posterSizes[3],
            detailPageSize = this.posterSizes[4],
        )
    }
}
