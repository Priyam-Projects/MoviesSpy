package com.example.moviesspy.presentation.screen.homepage

import com.example.moviesspy.domain.model.Movie

sealed interface HomePageUiState {

    data class Loading(val message: String) : HomePageUiState

    data class Success(val movies: List<Movie>) : HomePageUiState

    data class Error(val message: String) : HomePageUiState
}
