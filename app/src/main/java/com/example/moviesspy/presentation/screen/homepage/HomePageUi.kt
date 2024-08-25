package com.example.moviesspy.presentation.screen.homepage

import androidx.compose.foundation.layout.Column
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
import androidx.navigation.NavController
import com.example.moviesspy.common.Constants
import com.example.moviesspy.domain.model.Movie
import com.example.moviesspy.presentation.screen.Screen
import com.example.moviesspy.presentation.screen.commonui.ErrorPageUi
import com.example.moviesspy.presentation.screen.commonui.LoadingUi
import com.example.moviesspy.presentation.screen.detailspage.DetailsPageViewModel
import com.example.moviesspy.presentation.screen.homepage.components.MovieItemUi
import com.example.moviesspy.presentation.screen.homepage.components.SearchBarUi

@Composable
fun HomePageUi(
    viewModel: HomePageViewModel = hiltViewModel(),
    navController: NavController,
    detailsViewModel: DetailsPageViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        SearchBarUi(
            searchQuery = searchQuery,
            onQueryChange = {
                viewModel.onSearchQueryChange(it)
            }
        )

        Spacer(modifier = Modifier.height(20.dp))
        HomePage(
            uiState = uiState,
            onMovieClick = {
                detailsViewModel.updateSelectedMovie(it)
                navController.navigate(Screen.DetailPageScreen.route)
            },
        )
    }
}

@Composable
fun HomePage(
    uiState: HomePageUiState,
    onMovieClick: (Movie) -> Unit,
) {
    when (uiState) {
        is HomePageUiState.Success -> {
            MovieGrid(
                movies = uiState.movies,
                onMovieClick = onMovieClick,
            )
        }

        is HomePageUiState.Loading -> {
            LoadingUi()
        }

        is HomePageUiState.Error -> {
            ErrorPageUi(
                message = uiState.message
            )
        }
    }
}

@Composable
fun MovieGrid(
    movies: List<Movie>,
    onMovieClick: (Movie) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(Constants.NO_COLUMNS_MOVIES_GRID),
        modifier = Modifier.fillMaxWidth(),
    ) {
        items(movies) { movie ->
            MovieItemUi(
                movie = movie,
                onMovieClick = onMovieClick,
            )
        }
    }
}
