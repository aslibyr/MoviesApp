package com.app.moviesapp.data.ui_models

data class MovieDetailUIModel(
    val title: String = "",
    val overview: String = "",
    val movieId: String = "",
    val duration: String = "",
    val isFavorite: Boolean = false,
    val movieImage: String = "",
    val voteAvg: String = "",
    val releaseDate: String = ""
)
