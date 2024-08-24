package com.example.moviesspy.domain.model

data class Movie(
    val title: String,
    val overview: String,
    var homePageImage: Image,
    var detailsPageImage: Image,
)
