package com.app.moviesapp.custom.image

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.app.moviesapp.R

@Composable
fun MoviesImageView(
    imageUrl: String,
    modifier: Modifier,
    @DrawableRes errorResource: Int = R.drawable.person_error_placeholder,
    contentScale: ContentScale = ContentScale.Crop
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "",
        modifier = modifier,
        error = painterResource(id = errorResource),
        placeholder = painterResource(id = errorResource),
        contentScale = contentScale
    )
}