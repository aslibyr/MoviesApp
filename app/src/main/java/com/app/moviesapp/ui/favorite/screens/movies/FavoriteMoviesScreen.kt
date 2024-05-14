package com.app.moviesapp.ui.favorite.screens.movies

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.moviesapp.custom.buttons.ListResetButton
import com.app.moviesapp.custom.image.MoviesImageView
import com.app.moviesapp.data.ui_models.MovieDetailUIModel
import kotlinx.coroutines.launch

@Composable
fun FavoriteMoviesScreen(
    viewModel: FavoriteMoviesViewModel = hiltViewModel(),
    onMovieClick: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val listState = rememberLazyGridState()
    val scope = rememberCoroutineScope()
    val isScrollButtonVisible by remember {
        derivedStateOf { listState.firstVisibleItemIndex >= 2 }
    }

    val context = LocalContext.current
    if (uiState.isRemoved) {
        Toast.makeText(context, "Removed from favorites.", Toast.LENGTH_SHORT).show()
        viewModel.toastMessageShowed()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyVerticalGrid(columns = GridCells.Fixed(2), state = listState) {
                items(
                    items = uiState.favMovies,
                    key = { item: MovieDetailUIModel -> item.movieId }) {
                    FavoriteMoviesListItem(
                        movie = it,
                        onMovieClick = onMovieClick,
                        onFavoriteMovieClick = {
                            viewModel.removeFromFavoriteMovie(it)
                        })
                }
            }
            if (isScrollButtonVisible) {
                ListResetButton {
                    scope.launch {
                        listState.animateScrollToItem(0)
                    }
                }
            }
        }
    }
}

@Composable
fun FavoriteMoviesListItem(
    movie: MovieDetailUIModel,
    onMovieClick: (String) -> Unit,
    onFavoriteMovieClick: (MovieDetailUIModel) -> Unit
) {
    val favoriteIcon = if (movie.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
    val favoriteColor = if (movie.isFavorite) Color.Red else Color.White

    Card(
        modifier = Modifier
            .wrapContentSize()
            .clickable {
                onMovieClick(movie.movieId)
            }
            .padding(8.dp)
            .width(180.dp)
            .shadow(elevation = 8.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(),
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column {
                // Movie Image
                MoviesImageView(
                    imageUrl = movie.movieImage,
                    modifier = Modifier
                        .height(230.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                // Title
                Text(
                    text = movie.title,
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
            Icon(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(24.dp)
                    .clickable {
                        onFavoriteMovieClick(movie)
                    },
                imageVector = favoriteIcon,
                contentDescription = "",
                tint = favoriteColor,
            )
        }
    }
}
