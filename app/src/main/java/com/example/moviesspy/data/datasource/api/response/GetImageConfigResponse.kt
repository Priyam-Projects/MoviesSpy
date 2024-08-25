package com.example.moviesspy.data.datasource.api.response

import com.example.moviesspy.data.datasource.api.model.ImageConfigApiModel
import com.google.gson.annotations.SerializedName

data class GetImageConfigResponse(
    @SerializedName("images")
    val imageConfigApiModel: ImageConfigApiModel,
    @SerializedName("change_keys")
    val changeKeys: List<String>,
)
