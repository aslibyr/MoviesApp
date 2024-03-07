package com.app.moviesapp.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import coil.compose.AsyncImage
import com.app.moviesapp.base.BasePagingResponse
import com.app.moviesapp.data.response.MovieResponse
import com.app.moviesapp.ui.home.HomeScreenViewModel
import com.app.moviesapp.utils.ResultWrapper

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()

) {
    val context = LocalContext.current
    val nowPlayingState by viewModel.nowPlaying.collectAsState()
    val popularState by viewModel.popular.collectAsState()
    val topRatedState by viewModel.topRated.collectAsState()
    val upcomingState by viewModel.upcoming.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        when (nowPlayingState) {
            is ResultWrapper.GenericError -> {
                val response = (nowPlayingState as ResultWrapper.GenericError).error
                response?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }

            ResultWrapper.Loading -> {}
            ResultWrapper.NetworkError -> {

            }

            is ResultWrapper.Success -> {
                val response = (nowPlayingState as ResultWrapper.Success<BasePagingResponse<MovieResponse>>).value
                MoviesWidget(movies = response.results, "Now Playing")
            }
        }
        when (popularState) {
            is ResultWrapper.GenericError -> {}
            ResultWrapper.Loading -> {}
            ResultWrapper.NetworkError -> TODO()
            is ResultWrapper.Success -> TODO()
        }
    }
}

@Composable
fun MoviesWidget(movies: List<MovieResponse>, category: String) {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = category, style = MaterialTheme.typography.bodyMedium
            )
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )


        }
        LazyRow() {
            items(movies) {
                MoviesWidgetItem(movie = it)
            }
        }
    }
}

@Composable
fun MoviesWidgetItem(movie: MovieResponse) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(model = movie.getImagePath(), contentDescription = "")
        Text(
            text = movie
                .title, style = MaterialTheme.typography.bodyMedium
        )

    }

}