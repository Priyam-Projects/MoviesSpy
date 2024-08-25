package com.example.moviesspy.presentation.screen.detailspage

import com.example.moviesspy.domain.model.Movie

sealed interface DetailsPageUiState {

    data class Loading(val message: String) : DetailsPageUiState

    data class Success(val data: Movie) : DetailsPageUiState

    data class Error(val message: String) : DetailsPageUiState
}