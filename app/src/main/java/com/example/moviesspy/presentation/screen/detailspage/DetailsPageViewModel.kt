package com.example.moviesspy.presentation.screen.detailspage

import androidx.lifecycle.ViewModel
import com.example.moviesspy.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailsPageViewModel @Inject constructor(
): ViewModel() {
    private val _uiState = MutableStateFlow<DetailsPageUiState>(DetailsPageUiState.Loading(""))
    val uiState = _uiState.asStateFlow()

    fun updateSelectedMovie(movie: Movie) {
        _uiState.value = DetailsPageUiState.Success(movie)
    }
}