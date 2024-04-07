package com.app.moviesapp.ui.favorite.screens.people

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FavoritePeopleScreen(viewModel: FavoritePeopleViewModel = hiltViewModel()) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "FAVPEOPLETEST")
    }

}