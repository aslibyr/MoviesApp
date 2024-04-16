package com.app.moviesapp.ui.detail.screens.videos

import com.app.moviesapp.data.ui_models.MovieVideoUIModel

data class VideosUIStateModel(
    val videoData: List<MovieVideoUIModel> = emptyList(),
    val isError: Boolean = false,
    val isLoading: Boolean = true,
    val errorMessage: String = "",
)