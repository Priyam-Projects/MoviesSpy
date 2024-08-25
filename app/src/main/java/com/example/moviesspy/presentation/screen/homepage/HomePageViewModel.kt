package com.example.moviesspy.presentation.screen.homepage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesspy.common.ApiResult
import com.example.moviesspy.domain.config.ImageConfig
import com.example.moviesspy.domain.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val repository: MoviesRepository,
): ViewModel() {

    private val TAG = "HomePageViewMode"

    // TODO: use initial state as a parameter such that it retains, ig maybe using savedInstanceBundle
    private val _uiState = MutableStateFlow<HomePageUiState>(HomePageUiState.Loading(""))
    val uiState = _uiState.asStateFlow()
    private lateinit var config: ImageConfig

    init {
        initHomePage()
    }

    private fun initHomePage() {
        viewModelScope.launch {
            updateImageConfig()
            updateTrendingMovies()
        }
    }

    private suspend fun updateTrendingMovies() {
        if (!this::config.isInitialized) {
            Log.d(TAG, "Image config not initialized")
            return
        }
        repository.getTrendingMovies(config).also {
            _uiState.value = when (it) {

                is ApiResult.Error -> {
                    HomePageUiState.Error(
                        message = it.message,
                    )
                }
                is ApiResult.Success -> {
                    HomePageUiState.Success(
                        movies = it.data,
                    )
                }
            }
        }
    }

    private suspend fun updateImageConfig() {
        repository.getImageConfig().also {
            when (it) {
                is ApiResult.Success -> config = it.data

                is ApiResult.Error -> {
                    _uiState.value = HomePageUiState.Error(
                        message = it.message,
                    )
                }
            }
        }
    }

}