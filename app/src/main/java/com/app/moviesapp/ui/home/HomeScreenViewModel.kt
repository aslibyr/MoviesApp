package com.app.moviesapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.app.moviesapp.data.response.MovieResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {
private val _movieState: MutableStateFlow<PagingData<MovieResponse>> =
    MutableStateFlow(value = PagingData.empty())


    private fun getNowPlaying(){

    }

}