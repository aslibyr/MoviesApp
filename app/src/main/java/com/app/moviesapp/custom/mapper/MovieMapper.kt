package com.app.moviesapp.custom.mapper

import com.app.moviesapp.custom.widget.MovieWidgetModel
import com.app.moviesapp.data.response.MovieResponse


fun MovieResponse.MovieWidgetModel() : MovieWidgetModel {
    return MovieWidgetModel(
        movieId = this.id.toString(),
        movieName = this.title,
        movieImage = this.getImagePath()
    )
}