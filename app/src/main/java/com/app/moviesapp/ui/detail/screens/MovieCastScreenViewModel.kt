package com.app.moviesapp.ui.detail.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.app.moviesapp.data.WebService
import javax.inject.Inject

class MovieCastScreenViewModel @Inject constructor(
    private val webService: WebService,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

}