package com.app.moviesapp.data.mapper

import com.app.moviesapp.data.response.PersonResponse
import com.app.moviesapp.data.ui_models.PersonUIModel

fun PersonResponse.PersonUIModel(): PersonUIModel {
    return PersonUIModel(
        personId = this.id ?: 0,
        birthday = this.birthday ?: "",
        biography = this.biography ?: "",
        name = this.name ?: "",
        placeOfBirth = this.place_of_birth ?: "",
        profilePath = this.getImagePath()
    )
}