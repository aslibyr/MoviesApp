package com.app.moviesapp.custom.widget

data class MovieWidgetComponentModel(
    val widgetCategory: String = "",
    val movies: List<MovieWidgetModel>
)

data class MovieWidgetModel(
    val movieId: String = "",
    val movieName: String = "",
    val movieImage: String = "",
    val voteAvg: String = "",
    val releaseDate: String = ""
)
