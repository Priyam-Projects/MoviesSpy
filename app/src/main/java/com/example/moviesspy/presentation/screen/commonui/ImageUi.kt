package com.example.moviesspy.presentation.screen.commonui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesspy.domain.model.Image

@Composable
fun ImageUi(
    image: Image,
    modifier: Modifier,
) {
    // TODO: Add a placeholder for image & loading animations
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(image.url)
            .crossfade(true)
            .build(),
        contentDescription = image.movieTitle,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}