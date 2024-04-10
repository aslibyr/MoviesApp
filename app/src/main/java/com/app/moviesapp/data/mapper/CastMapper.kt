package com.app.moviesapp.data.mapper

import com.app.moviesapp.custom.widget.CastWidgetModel
import com.app.moviesapp.data.response.MovieCreditResponseItem
import com.app.moviesapp.data.ui_models.MovieCastUIModel

fun MovieCreditResponseItem.CastWidgetModel(): CastWidgetModel {
    return CastWidgetModel(
        castName = this.name ?: "",
        character = this.character ?: "",
        profilePath = this.getImagePath(),
        personId = this.id ?: 0
    )
}

fun MovieCreditResponseItem.MovieCastUIModel(): MovieCastUIModel {
    return MovieCastUIModel(
        castName = this.name ?: "",
        character = this.character ?: "",
        profilePath = this.getImagePath(),
        personId = this.id.toString()
    )
}

