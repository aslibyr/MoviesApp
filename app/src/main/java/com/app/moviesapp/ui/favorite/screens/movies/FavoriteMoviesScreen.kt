package com.app.moviesapp.ui.favorite.screens.movies

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.app.moviesapp.custom.buttons.ListResetButton
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
        Toast.makeText(context, "Favorilerden kaldırıldı", Toast.LENGTH_SHORT).show()
        viewModel.toastMessageShowed()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.onPrimary
                )
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
    Box(modifier = Modifier.wrapContentSize()) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp))
                .clickable {
                    onMovieClick(
                        movie.movieId
                    )
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(model = movie.movieImage, contentDescription = "")
            Text(
                text = movie.title,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Icon(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .clickable {
                    onFavoriteMovieClick(movie)
                }, imageVector = Icons.Default.Favorite, contentDescription = "",
            tint = Color.Red
        )
    }
}
