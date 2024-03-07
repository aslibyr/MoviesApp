package com.app.moviesapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.app.moviesapp.base.BasePagingResponse
import com.app.moviesapp.data.WebService
import com.app.moviesapp.data.response.MovieResponse
import com.app.moviesapp.utils.ResultWrapper
import com.app.moviesapp.utils.safeApiCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val webService: WebService) : ViewModel() {

    private val _nowPlaying =
        MutableStateFlow<ResultWrapper<BasePagingResponse<MovieResponse>>>(ResultWrapper.Loading)
    val nowPlaying: StateFlow<ResultWrapper<BasePagingResponse<MovieResponse>>> =
        _nowPlaying.stateIn(
            viewModelScope,
            SharingStarted.Eagerly, ResultWrapper.Loading
        )

    private val _popular =
        MutableStateFlow<ResultWrapper<BasePagingResponse<MovieResponse>>>(ResultWrapper.Loading)
    val popular: StateFlow<ResultWrapper<BasePagingResponse<MovieResponse>>> = _popular.stateIn(
        viewModelScope,
        SharingStarted.Eagerly, ResultWrapper.Loading
    )

    private val _topRated =
        MutableStateFlow<ResultWrapper<BasePagingResponse<MovieResponse>>>(ResultWrapper.Loading)
    val topRated: StateFlow<ResultWrapper<BasePagingResponse<MovieResponse>>> = _topRated.stateIn(
        viewModelScope,
        SharingStarted.Eagerly, ResultWrapper.Loading
    )

    private val _upcoming =
        MutableStateFlow<ResultWrapper<BasePagingResponse<MovieResponse>>>(ResultWrapper.Loading)
    val upcoming: StateFlow<ResultWrapper<BasePagingResponse<MovieResponse>>> = _upcoming.stateIn(
        viewModelScope,
        SharingStarted.Eagerly, ResultWrapper.Loading
    )

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            val nowPlaying = safeApiCall(Dispatchers.IO) { webService.getNowPLaying(1) }
            _nowPlaying.emit(nowPlaying)
        }
    }
}