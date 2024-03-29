package com.app.moviesapp.data.mapper

import com.app.moviesapp.custom.widget.MovieWidgetModel
import com.app.moviesapp.data.response.MovieDetailResponse
import com.app.moviesapp.data.response.MovieResponse
import com.app.moviesapp.data.ui_models.MovieDetailUIModel


fun MovieResponse.MovieWidgetModel() : MovieWidgetModel {
    return MovieWidgetModel(
        movieId = this.id.toString(),
        movieName = this.title,
        movieImage = this.getImagePath()
    )
}

fun MovieDetailResponse.toUIModel(): MovieDetailUIModel {
    return MovieDetailUIModel(
        title = this.title ?: "",
        overview = this.overview ?: "",
        movieId = this.id.toString(),
        duration = "${this.runtime} min."
    )
}