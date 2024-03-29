package com.app.moviesapp.ui.detail.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.app.moviesapp.custom.loading.LoadingDialog
import com.app.moviesapp.data.ui_models.MovieCastUIModel

@Composable
fun MovieCastScreen(viewModel: MovieCastScreenViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val listState = rememberLazyListState()

    if (uiState.isSuccess) {
        LazyColumn(modifier = Modifier.fillMaxSize(), state = listState) {
            items(uiState.movieCastListData, key = { it.castName }) {
                CastListItem(cast = it)
            }
        }
    }

    if (uiState.isLoading) {
        LoadingDialog()
    }

    if (uiState.isError) {
        Toast.makeText(context, "Network error", Toast.LENGTH_LONG).show()
    }
}

@Composable
fun CastListItem(cast: MovieCastUIModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .aspectRatio(1f),
                model = cast.profilePath,
                contentDescription = cast.castName,
                contentScale = ContentScale.Crop,
            )
            Column {
                Text(text = cast.castName)
                Text(text = cast.character)
            }
        }
    }
}