package com.app.moviesapp.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.moviesapp.custom.textfield.CustomOutlinedTextField

@Composable
fun SearchScreen(
    viewModel: SearchScreenViewModel = hiltViewModel(),
    onMovieClick: (String) -> Unit
) {

    var text by rememberSaveable {
        mutableStateOf("")
    }

    Column(modifier = Modifier.fillMaxSize()) {
        CustomOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(8.dp),
            label = "Ara",
            text = text,
            returnText = { text = it },
            onImeClicked = { ImeAction.Send },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
    }
}