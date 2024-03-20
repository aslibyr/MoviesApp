package com.app.moviesapp.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.moviesapp.custom.textfield.CustomOutlinedTextField
import com.app.moviesapp.ui.movie_list.MoviesListItem
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SearchScreen(
    viewModel: SearchScreenViewModel = hiltViewModel(),
    onMovieClick: (String) -> Unit
) {

    var text by rememberSaveable {
        mutableStateOf("")
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

//    if (text.length >= 2) {
//        viewModel.searchMovie(text, uiState.page)
//    }
    val state = rememberLazyGridState()

    Column(modifier = Modifier.fillMaxSize()) {
        CustomOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(8.dp),
            label = "Ara",
            text = text,
            returnText = { text = it },
            onImeClicked = {
                           viewModel.searchMovie(text,1)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),

        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = state
        ) {
            items(uiState.movies) {movie ->
                MoviesListItem(movie = movie, onMovieClick = onMovieClick)
            }
        }

        LaunchedEffect(uiState.page) {
            snapshotFlow { state.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
                .collectLatest { index ->
                    val triggerPoint = (uiState.movies.size - 10) // 10. elemandan sonra yeni bir istek yap

                    if (index != null && index >= triggerPoint && uiState.page < uiState.totalPages) {
                        // Yeni sayfayı yükle
                        viewModel.searchMovie(text, uiState.page + 1)
                    }

                }
        }
    }
}