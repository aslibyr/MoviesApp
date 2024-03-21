package com.app.moviesapp.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.moviesapp.base.BasePagingResponse
import com.app.moviesapp.data.WebService
import com.app.moviesapp.data.response.MovieCreditResponseItem
import com.app.moviesapp.data.response.MovieDetailResponse
import com.app.moviesapp.data.response.MovieResponse
import com.app.moviesapp.data.response.MovieReviewsResponse
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
        getMovieCredits(id = id)
        getMovieRecommendations(id = id, 1)
        getMovieSimilar(id = id, 1)
        getMovieReviews(id = id, 1)
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
                            successCount = _uiState.value.successCount.plus(1)
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

    private fun getMovieCredits(id: String) {
        viewModelScope.launch {
            when (val response = safeApiCall(Dispatchers.IO) {
                webService.getMovieCredits(id)
            }) {
                is ResultWrapper.Success -> {
                    response.value.cast?.let { castList ->
                        _uiState.update {
                            it.copy(
                                movieCastData = castList,
                                successCount = _uiState.value.successCount.plus(1),
                                isLoading = false,
                            )
                        }
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

    private fun getMovieRecommendations(id: String, page: Int) {
        viewModelScope.launch {
            when (val response = safeApiCall(Dispatchers.IO) {
                webService.getRecommendations(id, page)
            }) {
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

                is ResultWrapper.Success -> {
                    _uiState.update {
                        it.copy(
                            movieRecommendations = response.value,
                            isLoading = false,
                            successCount = _uiState.value.successCount.plus(1)
                        )
                    }
                }
            }
        }
    }

    private fun getMovieSimilar(id: String, page: Int) {
        viewModelScope.launch {
            when (val response = safeApiCall(Dispatchers.IO) {
                webService.getSimilar(id, page)
            }) {
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

                is ResultWrapper.Success -> {
                    _uiState.update {
                        it.copy(
                            movieSimilar = response.value,
                            isLoading = false,
                            successCount = _uiState.value.successCount.plus(1)
                        )
                    }
                }
            }
        }
    }

    private fun getMovieReviews(id: String, page: Int) {
        viewModelScope.launch {
            when (val response = safeApiCall(Dispatchers.IO) {
                webService.getReviews(id, page)
            }) {
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

                is ResultWrapper.Success -> {
                    _uiState.update {
                        it.copy(
                            movieReviewsData = response.value,
                            isLoading = false,
                            successCount = _uiState.value.successCount.plus(1)
                        )
                    }
                }
            }
        }
    }
}

data class MovieDetailUIStateModel(
    val movieDetailData: MovieDetailResponse? = null,
    val movieCastData: List<MovieCreditResponseItem> = emptyList(),
    val movieRecommendations: BasePagingResponse<MovieResponse>? = null,
    val movieSimilar: BasePagingResponse<MovieResponse>? = null,
    val movieReviewsData: MovieReviewsResponse? = null,
    val isLoading: Boolean = true,
    val errorMessage: String = "",
    val successCount: Int = 0
)