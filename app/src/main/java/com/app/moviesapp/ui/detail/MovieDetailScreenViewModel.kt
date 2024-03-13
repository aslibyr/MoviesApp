package com.app.moviesapp.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.moviesapp.data.WebService
import com.app.moviesapp.data.response.MovieDetailResponse
import com.app.moviesapp.utils.ResultWrapper
import com.app.moviesapp.utils.safeApiCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemDetailScreenViewModel @Inject constructor(
    private val webService: WebService,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val id = checkNotNull(savedStateHandle.get<String>("id"))

    private val _uiState = MutableStateFlow(MovieDetailUIStateModel())
    val uiState = _uiState.asStateFlow()

    init {
        getMovieDetails(id = id)
    }


    private fun getMovieDetails(id: String) {
        viewModelScope.launch {
            when (val response = safeApiCall(Dispatchers.IO) {
                webService.getMovieDetails(id)
            }) {
                is ResultWrapper.Success -> {
                    _uiState.update {
                        it.copy(
                            movieDetailData = response.value,
                            isLoading = false,
                            isSuccess = true
                        )
                    }
                }

                is ResultWrapper.GenericError -> {
                    _uiState.update {
                        it.copy(errorMessage = response.error.toString())
                    }
                }

                ResultWrapper.Loading -> {
                    _uiState.update {
                        it.copy(isLoading = true)
                    }
                }

                ResultWrapper.NetworkError -> {
                    _uiState.update {
                        it.copy(errorMessage = "İnternet bağlantınızı kontrol edin.")
                    }
                }
            }
        }
    }
}

data class MovieDetailUIStateModel(
    val movieDetailData: MovieDetailResponse? = null,
    val isLoading: Boolean = true,
    val errorMessage: String = "",
    val isSuccess: Boolean = false
)