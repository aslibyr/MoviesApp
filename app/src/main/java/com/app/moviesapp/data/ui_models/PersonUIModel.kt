package com.app.moviesapp.data.ui_models

data class PersonUIModel(
    val personId: Int = 0,
    val birthday: String = "",
    val biography: String = "",
    val name: String = "",
    val placeOfBirth: String = "",
    val profilePath: String = "",
    val isFavorite: Boolean = false
)