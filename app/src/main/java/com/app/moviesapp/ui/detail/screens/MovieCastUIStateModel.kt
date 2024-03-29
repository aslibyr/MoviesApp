package com.app.moviesapp.ui.detail.screens

import com.app.moviesapp.data.ui_models.MovieCastUIModel

data class MovieCastUIStateModel(
    val movieCastListData: List<MovieCastUIModel> = emptyList(),
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
)