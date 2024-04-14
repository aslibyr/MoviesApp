package com.app.moviesapp.custom.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.app.moviesapp.R
import com.app.moviesapp.custom.image.MoviesImageView

@Composable
fun MovieWidget(
    model: MovieWidgetComponentModel,
    openListScreen: () -> Unit,
    onMovieClick: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clickable { openListScreen() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = model.widgetCategory, style = MaterialTheme.typography.bodyMedium
            )
            Icon(
                imageVector = Icons.Filled.ArrowForwardIos,
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
        }
        LazyRow(Modifier.fillMaxWidth(), contentPadding = PaddingValues(start = 16.dp)) {
            items(model.movies) {
                MovieWidgetItem(movie = it, onMovieClick = onMovieClick)
            }
        }
    }
}

@Composable
fun MovieWidgetItem(movie: MovieWidgetModel, onMovieClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                onMovieClick(movie.movieId)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .width(180.dp),
            elevation = CardDefaults.cardElevation(5.dp),
            shape = RoundedCornerShape(15.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            MoviesImageView(
                imageUrl = movie.movieImage, modifier = Modifier
                    .height(250.dp)
                    .width(180.dp),
                errorResource = R.drawable.movie_error_placeholder
            )

            Text(
                modifier = Modifier.padding(8.dp),
                text = movie.movieName,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}