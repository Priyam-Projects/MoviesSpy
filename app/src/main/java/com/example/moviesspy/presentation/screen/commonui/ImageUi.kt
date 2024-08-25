package com.example.moviesspy.presentation.screen.commonui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesspy.common.Dimens
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
        placeholder = ColorPainter(Color.LightGray),
        modifier = modifier
            .clip(RoundedCornerShape(Dimens.imageRadius))
    )

}