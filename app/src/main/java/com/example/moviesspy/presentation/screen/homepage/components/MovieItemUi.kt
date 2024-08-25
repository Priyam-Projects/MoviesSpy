package com.example.moviesspy.presentation.screen.homepage.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.unit.dp
import com.example.moviesspy.common.Dimens
import com.example.moviesspy.domain.model.Movie
import com.example.moviesspy.presentation.screen.commonui.ImageUi

@Composable
fun MovieItemUi(
    movie: Movie,
    onMovieClick: (Movie) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column (
        modifier = modifier
            .clickable { onMovieClick(movie) }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        ImageUi(
            image = movie.homePageImage,
            modifier = Modifier
                .size(Dimens.homePageImageSize)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movie.title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}