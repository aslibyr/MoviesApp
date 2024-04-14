package com.app.moviesapp.ui.favorite.screens.people

import com.app.moviesapp.data.ui_models.PersonUIModel

data class FavoritePeopleUIStateModel(
    val favPeople: List<PersonUIModel> = emptyList(),
    val isRemoved: Boolean = false
)
