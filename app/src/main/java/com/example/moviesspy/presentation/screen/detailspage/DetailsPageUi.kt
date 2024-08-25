package com.example.moviesspy.presentation.screen.detailspage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.moviesspy.common.Dimens
import com.example.moviesspy.domain.model.Movie
import com.example.moviesspy.presentation.screen.commonui.ImageUi
import com.example.moviesspy.presentation.screen.detailspage.components.IconButtonUi

// todo make it modular functions
@Composable
fun DetailsPageUi (
    movie: Movie,
) {

    Column (
        modifier = Modifier
            .padding(20.dp)
    ) {
        IconButtonUi(
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            onClick = {},
        )

        Spacer(modifier = Modifier.height(20.dp))

        ImageUi(
            image = movie.detailsPageImage,
            modifier = Modifier
                .width(Dimens.detailsPageImageWidth)
                .height(Dimens.detailsPageImageHeight)
                .align(alignment = Alignment.CenterHorizontally)
        )

        Text(
            text = movie.title,
            style = MaterialTheme.typography.headlineMedium,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = movie.overview,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify
        )
    }
}
