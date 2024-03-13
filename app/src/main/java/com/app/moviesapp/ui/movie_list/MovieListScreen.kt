package com.app.moviesapp.ui.movie_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.app.moviesapp.data.response.MovieResponse
import com.app.moviesapp.utils.Constant
import com.app.moviesapp.utils.ScreenRoutes


@Composable
fun MovieListScreen(
    viewModel: MovieListScreenViewModel = hiltViewModel(),
    onMovieClick: (String) -> Unit
) {

    val movies: LazyPagingItems<MovieResponse> =
        viewModel.movies.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.onPrimary
            ), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(movies.itemCount) {
                MoviesListItem(movie = movies[it]!!, onMovieClick = onMovieClick)
            }
        }
    }
}

@Composable
fun MoviesListItem(movie: MovieResponse, onMovieClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp))
            .clickable {
                onMovieClick(
                    ScreenRoutes.MOVIE_DETAIL_ROUTE.replace(
                        oldValue = Constant.ID,
                        newValue = movie.id.toString()
                    )
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(model = movie.getImagePath(), contentDescription = "")
        Text(
            text = movie.title,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}