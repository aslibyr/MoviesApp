package com.app.moviesapp.ui.detail.screens.videos

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.moviesapp.data.repository.MovieRepository
import com.app.moviesapp.utils.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailVideoScreenViewModel @Inject constructor(
    private val repository: MovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val id = checkNotNull(savedStateHandle.get<String>("movie_id"))

    private val _uiState = MutableStateFlow(VideosUIStateModel())
    val uiState = _uiState.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(),
        VideosUIStateModel()
    )

    init {
        getVideos(movieId = id)
    }

    private fun getVideos(movieId: String) {
        viewModelScope.launch() {
            when (val response = repository.getVideos(movieId)
            ) {
                is ResultWrapper.GenericError -> {}
                ResultWrapper.Loading -> {}
                ResultWrapper.NetworkError -> {}
                is ResultWrapper.Success -> {
                    val videos = response.value
                    _uiState.value = _uiState.value.copy(videoData = videos, isLoading = false)
                }
            }
        }
    }
}