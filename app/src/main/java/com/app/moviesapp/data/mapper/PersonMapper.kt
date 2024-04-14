package com.app.moviesapp.data.mapper

import com.app.moviesapp.data.local.entity.FavoritePersonEntity
import com.app.moviesapp.data.response.PersonResponse
import com.app.moviesapp.data.ui_models.PersonUIModel

fun PersonResponse.PersonUIModel(isFavorite: Boolean): PersonUIModel {
    return PersonUIModel(
        personId = this.id ?: 0,
        biography = this.biography ?: "",
        name = this.name ?: "",
        profilePath = this.getImagePath(),
        isFavorite = isFavorite
    )
}

fun PersonUIModel.toFavoritePersonEntity(): FavoritePersonEntity {
    return FavoritePersonEntity(
        personId = this.personId,
        name = this.name,
        profilePath = this.profilePath,
    )
}

fun FavoritePersonEntity.toUIModel(): PersonUIModel {
    return PersonUIModel(
        profilePath = this.profilePath,
        personId = this.personId,
        name = this.name,
        isFavorite = true
    )
}