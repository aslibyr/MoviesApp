package com.app.moviesapp.data.mapper

import com.app.moviesapp.custom.widget.CastWidgetModel
import com.app.moviesapp.data.response.MovieCreditResponseItem

fun MovieCreditResponseItem.CastWidgetModel(): CastWidgetModel {
    return CastWidgetModel(
        castName = this.name ?: "",
        character = this.character ?: "",
        profilePath = this.getImagePath(),
        personId = this.id ?: 0
    )
}