package com.app.moviesapp.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.app.moviesapp.custom.navigation.graphs.MovieListType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeListScreenViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel(){
    val type = checkNotNull(savedStateHandle.get<String>("type"))

    init {
        getData()
    }

    private fun getData() {
        when (type){
            MovieListType.UPCOMING.type ->  {

            }
            MovieListType.TOP_RATED.type ->  {

            }
            MovieListType.NOW_PLAYING.type ->  {

            }
            MovieListType.POPULAR.type ->  {

            }
        }
    }
}