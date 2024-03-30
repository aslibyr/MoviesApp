package com.app.moviesapp.ui.detail.screens.cast

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.moviesapp.data.WebService
import com.app.moviesapp.data.mapper.MovieCastUIModel
import com.app.moviesapp.utils.ResultWrapper
import com.app.moviesapp.utils.safeApiCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieCastScreenViewModel @Inject constructor(
    private val webService: WebService,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow(MovieCastUIStateModel())
    val uiState = _uiState.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(),
        MovieCastUIStateModel()
    )
    private val id = checkNotNull(savedStateHandle.get<String>("movie_id"))

    init {
        getMovieCast(id)
    }

    private fun getMovieCast(id: String) {
        viewModelScope.launch {
            when (val response = safeApiCall(Dispatchers.IO) {
                webService.getMovieCredits(id)
            }) {
                is ResultWrapper.Success -> {
                    response.value.cast.let { castList ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                movieCastListData = castList.map { it.MovieCastUIModel() },
                                isSuccess = true

                            )
                        }
                    }
                }

                is ResultWrapper.GenericError -> {
                    _uiState.update {
                        it.copy(errorMessage = response.error.toString(), isLoading = false)
                    }
                }

                ResultWrapper.Loading -> {
                    _uiState.update {
                        it.copy(isLoading = true)
                    }
                }

                ResultWrapper.NetworkError -> {
                    _uiState.update {
                        it.copy(
                            errorMessage = "İnternet bağlantınızı kontrol edin.",
                            isLoading = false,
                        )
                    }
                }
            }
        }
    }
}