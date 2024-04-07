package com.app.moviesapp.ui.favorite.screens.movies

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun FavoriteMoviesScreen(viewModel: FavoriteMoviesViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(visible = uiState.favMovies.isNotEmpty()) {
            LazyColumn {
                items(uiState.favMovies) {
                    Text(text = it.title)
                }
            }
        }
    }

}
