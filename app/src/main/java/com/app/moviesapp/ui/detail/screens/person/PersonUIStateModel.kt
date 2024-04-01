package com.app.moviesapp.ui.detail.screens.person

import com.app.moviesapp.data.ui_models.PersonUIModel

data class PersonUIStateModel(
    val images: List<String> = emptyList(),
    val personData: PersonUIModel = PersonUIModel(),
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
)