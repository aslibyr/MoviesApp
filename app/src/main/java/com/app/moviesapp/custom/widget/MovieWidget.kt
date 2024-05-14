package com.app.moviesapp.custom.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                modifier = Modifier.weight(1f),
                text = model.widgetCategory, style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "View all",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Icon(
                imageVector = Icons.Filled.ArrowForwardIos,
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)
                    .padding(start = 8.dp),
                tint = MaterialTheme.colorScheme.secondary
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
    Card(
        modifier = Modifier
            .wrapContentSize()
            .clickable {
                onMovieClick(movie.movieId)
            }
            .padding(8.dp)
            .width(150.dp)
            .shadow(elevation = 8.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Movie Image
            MoviesImageView(
                imageUrl = movie.movieImage,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )
            // Title
            Text(
                text = movie.movieName,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 12.sp,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp)
            )
            // Rating and Release Date
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = movie.releaseDate.take(4),
                    fontWeight = FontWeight.Light,
                    color = Color.White,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .weight(1f)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Rating",
                    modifier = Modifier
                        .size(14.dp)
                        .padding(end = 4.dp),
                    tint = Color.White
                )
                Text(
                    text = movie.voteAvg.take(3),
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}
