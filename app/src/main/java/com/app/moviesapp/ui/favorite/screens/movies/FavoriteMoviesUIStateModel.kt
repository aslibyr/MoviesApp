package com.app.moviesapp.ui.favorite.screens.movies

import com.app.moviesapp.data.ui_models.MovieDetailUIModel

data class FavoriteMoviesUIStateModel(
    val favMovies: List<MovieDetailUIModel> = emptyList(),
)