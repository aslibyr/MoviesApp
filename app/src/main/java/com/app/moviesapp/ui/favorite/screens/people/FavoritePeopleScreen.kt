package com.app.moviesapp.ui.favorite.screens.people

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.app.moviesapp.custom.buttons.ListResetButton
import com.app.moviesapp.data.ui_models.PersonUIModel
import kotlinx.coroutines.launch

@Composable
fun FavoritePeopleScreen(
    viewModel: FavoritePeopleViewModel = hiltViewModel(),
    onPersonClick: (String) -> Unit
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
            .background(
                MaterialTheme.colorScheme.onPrimary
            )
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(2), state = listState) {
            items(
                items = uiState.favPeople,
                key = { item: PersonUIModel -> item.personId }) {
                FavoritePersonListItem(
                    person = it,
                    onPersonClick = onPersonClick,
                    onFavoritePersonClick = {
                        viewModel.removeFromFavoritePerson(it)
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

@Composable
fun FavoritePersonListItem(
    person: PersonUIModel,
    onPersonClick: (String) -> Unit,
    onFavoritePersonClick: (PersonUIModel) -> Unit
) {
    Box(modifier = Modifier.wrapContentSize()) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp))
                .clickable {
                    onPersonClick(
                        person.personId.toString()
                    )
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(model = person.profilePath, contentDescription = "")
            Text(
                text = person.name,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Icon(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .clickable {
                    onFavoritePersonClick(person)
                }, imageVector = Icons.Default.Favorite, contentDescription = ""
        )
    }
}
