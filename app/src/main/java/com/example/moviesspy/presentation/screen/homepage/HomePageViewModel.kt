package com.example.moviesspy.presentation.screen.homepage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesspy.common.ApiResult
import com.example.moviesspy.domain.config.ImageConfig
import com.example.moviesspy.domain.model.Movie
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

    private val _uiState = MutableStateFlow<HomePageUiState>(HomePageUiState.Loading(""))
    val uiState = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private lateinit var config: ImageConfig
    private lateinit var allMovies: List<Movie>

    init {
        initHomePage()
    }

    private fun initHomePage() {
        viewModelScope.launch {
            updateImageConfig()
            println("Nayam updateImageDone done")
            updateTrendingMovies()
        }
    }

    private suspend fun updateTrendingMovies() {
        if (!this::config.isInitialized) {
            Log.d(TAG, "Image config not initialized")
            return
        }
        println("Nayam update trending movies")
        repository.getTrendingMovies(config).also {
            _uiState.value = when (it) {

                is ApiResult.Error -> {
                    HomePageUiState.Error(
                        message = it.message,
                    )
                }
                is ApiResult.Success -> {
                    allMovies = it.data
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

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        _uiState.value = HomePageUiState.Loading("")

        val filteredMovies = filterMovies(query)
        _uiState.value = when {
            filteredMovies.isEmpty() -> {
                HomePageUiState.Error("No movies found")
            }
            else -> {
                HomePageUiState.Success(filteredMovies)
            }
        }
    }

    // TODO: Either move these to use cases or use strategy design pattern
    private fun filterMovies(query: String): List<Movie> {
        if (query.isBlank()) {
            return allMovies
        }
        val lowerCaseQuery = query.lowercase()
        return allMovies.filter {
            it.title.lowercase().contains(lowerCaseQuery)
        }
    }

}