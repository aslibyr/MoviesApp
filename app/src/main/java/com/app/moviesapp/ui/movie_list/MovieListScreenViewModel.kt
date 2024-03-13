package com.app.moviesapp.ui.movie_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.moviesapp.custom.navigation.graphs.MovieListType
import com.app.moviesapp.data.WebService
import com.app.moviesapp.data.paging.MoviePagingSource
import com.app.moviesapp.data.response.MovieResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val webService: WebService
) : ViewModel() {
    val type = checkNotNull(savedStateHandle.get<String>("type"))

    private val _movies = MutableStateFlow<PagingData<MovieResponse>>(PagingData.empty())
    val movies: StateFlow<PagingData<MovieResponse>>
        get() = _movies

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            when (type) {
                MovieListType.UPCOMING.type -> {
                    Pager(PagingConfig(pageSize = 20)) {
                        MoviePagingSource(webService, MovieListType.UPCOMING)
                    }.flow.cachedIn(viewModelScope).collect {
                        _movies.emit(it)
                    }
                }


                MovieListType.TOP_RATED.type -> {
                    Pager(PagingConfig(pageSize = 20)) {
                        MoviePagingSource(webService, MovieListType.TOP_RATED)
                    }.flow.cachedIn(viewModelScope).collect {
                        _movies.emit(it)
                    }
                }

                MovieListType.NOW_PLAYING.type -> {
                    Pager(PagingConfig(pageSize = 20)) {
                        MoviePagingSource(webService, MovieListType.NOW_PLAYING)
                    }.flow.cachedIn(viewModelScope).collect {
                        _movies.emit(it)
                    }
                }

                MovieListType.POPULAR.type -> {
                    Pager(PagingConfig(pageSize = 20)) {
                        MoviePagingSource(webService, MovieListType.POPULAR)
                    }.flow.cachedIn(viewModelScope).collect {
                        _movies.emit(it)
                    }
                }
            }
        }
    }
}