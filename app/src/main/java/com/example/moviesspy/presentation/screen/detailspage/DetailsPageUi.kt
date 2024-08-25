package com.example.moviesspy.presentation.screen.detailspage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.moviesspy.common.Dimens
import com.example.moviesspy.domain.model.Movie
import com.example.moviesspy.presentation.screen.commonui.ErrorPageUi
import com.example.moviesspy.presentation.screen.commonui.ImageUi
import com.example.moviesspy.presentation.screen.commonui.LoadingUi
import com.example.moviesspy.presentation.screen.detailspage.components.IconButtonUi
import com.example.moviesspy.presentation.screen.homepage.HomePageUiState

// todo make it modular functions
@Composable
fun DetailsPageUi (
    viewModel: DetailsPageViewModel,
    navController: NavController,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column (
        modifier = Modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        IconButtonUi(
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            onClick = {
                navController.popBackStack()
            },
        )

        Spacer(modifier = Modifier.height(20.dp))

        when (uiState) {
            is DetailsPageUiState.Success -> {
                MovieDetails(movie = (uiState as DetailsPageUiState.Success).data)
            }
            is DetailsPageUiState.Error -> {
                ErrorPageUi(message = (uiState as DetailsPageUiState.Error).message)
            }
            is DetailsPageUiState.Loading -> {
                LoadingUi()
            }
        }
    }
}

@Composable
fun MovieDetails(
    movie: Movie,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ImageUi(
            image = movie.detailsPageImage,
            modifier = Modifier
                .size(Dimens.detailsPageImageSize)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = movie.title,
            style = MaterialTheme.typography.headlineLarge,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = movie.overview,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Justify
        )
    }
}
