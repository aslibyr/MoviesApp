package com.app.moviesapp.ui.detail.ui_models

import com.app.moviesapp.data.response.MovieDetailResponse

data class MovieDetailUIModel(
    val title: String = "",
    val overview: String = "",
    val movieId : String = ""
)

fun MovieDetailResponse.toUIModel(): MovieDetailUIModel {
    return MovieDetailUIModel(
        title = this.title ?: "",
        overview = this.overview ?: "",
        movieId = this.id.toString()
    )
}
