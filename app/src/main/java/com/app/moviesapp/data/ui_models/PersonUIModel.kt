package com.app.moviesapp.data.ui_models

data class PersonUIModel(
    val personId: Int = 0,
    val biography: String = "",
    val name: String = "",
    val profilePath: String = "",
    val isFavorite: Boolean = false
)