package com.app.moviesapp.ui.movie_list

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
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.moviesapp.custom.buttons.ListResetButton
import com.app.moviesapp.custom.image.MoviesImageView
import com.app.moviesapp.data.response.MovieResponse
import kotlinx.coroutines.launch


@Composable
fun MovieListScreen(
    viewModel: MovieListScreenViewModel = hiltViewModel(),
    onMovieClick: (String) -> Unit
) {
    val movies: LazyPagingItems<MovieResponse> =
        viewModel.movies.collectAsLazyPagingItems()

    val listState = rememberLazyGridState()
    val scope = rememberCoroutineScope()
    val isScrollButtonVisible by remember {
        derivedStateOf { listState.firstVisibleItemIndex >= 3 }
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
                items(movies.itemCount) {
                    MoviesListItem(movie = movies[it]!!, onMovieClick = onMovieClick)
                }
            }
            if (movies.loadState.append is LoadState.Error) {
                Text(text = "error")
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
fun MoviesListItem(movie: MovieResponse, onMovieClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .clickable {
                onMovieClick(movie.id.toString())
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
                imageUrl = movie.getImagePath(),
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
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
                    text = movie.release_date?.take(4).toString(),
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
                movie.vote_average?.let {
                    Text(
                        text = it.take(3),
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }
    }
}