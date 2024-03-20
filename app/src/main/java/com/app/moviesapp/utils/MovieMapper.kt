package com.app.moviesapp.utils

import com.app.moviesapp.custom.widget.MovieWidgetModel
import com.app.moviesapp.data.response.MovieResponse
import com.app.moviesapp.data.response.ReviewResultsItem


fun MovieResponse.MovieWidgetModel() : MovieWidgetModel {
    return MovieWidgetModel(
        movieId = this.id.toString(),
        movieName = this.title,
        movieImage = this.getImagePath()
    )
}