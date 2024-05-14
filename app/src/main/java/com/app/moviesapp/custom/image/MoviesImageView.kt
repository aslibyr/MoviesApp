package com.app.moviesapp.custom.image

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.SubcomposeAsyncImage
import com.app.moviesapp.R

@Composable
fun MoviesImageView(
    imageUrl: String,
    modifier: Modifier,
    @DrawableRes errorResource: Int = R.drawable.person_error_placeholder,
    contentScale: ContentScale = ContentScale.Crop
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = imageUrl,
        contentDescription = "",
        loading = {
            CircularProgressIndicator()
        },
        error = {
            Image(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = errorResource),
                contentDescription = ""
            )
        },
        contentScale = contentScale
    )
}