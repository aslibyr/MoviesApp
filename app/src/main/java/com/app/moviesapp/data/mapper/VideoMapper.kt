package com.app.moviesapp.data.mapper

import com.app.moviesapp.data.response.ResultsItem
import com.app.moviesapp.data.ui_models.MovieVideoUIModel

fun ResultsItem.toUIModel(): MovieVideoUIModel {
    return MovieVideoUIModel(
        url = this.key ?: "",
        id = this.id ?: ""
    )
}
