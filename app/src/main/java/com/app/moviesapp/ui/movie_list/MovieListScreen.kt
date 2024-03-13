package com.app.moviesapp.ui.movie_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.moviesapp.data.response.MovieResponse


@Composable
fun MovieListScreen(viewModel: MovieListScreenViewModel = hiltViewModel()) {

    val movies: LazyPagingItems<MovieResponse> =
        viewModel.movies.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.onPrimary
            ), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        
        items(movies.itemCount) {
            Text(text = movies[it]?.title ?: "")
        }
    }
}