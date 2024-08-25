package com.example.moviesspy.presentation.screen.homepage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.size.Dimension
import com.example.moviesspy.common.Dimens
import com.example.moviesspy.domain.model.Movie
import com.example.moviesspy.presentation.screen.commonui.ErrorPageUi
import com.example.moviesspy.presentation.screen.homepage.components.MovieItemUi
import com.example.moviesspy.presentation.screen.homepage.components.SearchBarUi

@Composable
fun HomePageUi(
    viewModel: HomePageViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is HomePageUiState.Success, is HomePageUiState.Loading -> {
           HomePage(uiState)
        }
        is HomePageUiState.Error -> {
            ErrorPageUi(
                message = (uiState as HomePageUiState.Error).message
            )
        }
    }
}

@Composable
fun HomePage(uiState: HomePageUiState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        SearchBarUi(modifier = Modifier)
        Spacer(modifier = Modifier.height(40.dp))

        if (uiState is HomePageUiState.Success) {
            MovieGrid(uiState.movies)
        } else {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
        }
    }
}

@Composable
fun MovieGrid(movies: List<Movie>) {
    LazyVerticalGrid(
        columns = GridCells.FixedSize(Dimens.homePageImageWidth),
        contentPadding = PaddingValues(20.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        items(movies) { movie ->
            MovieItemUi(movie = movie, onMovieClick = {})
        }
    }
}
