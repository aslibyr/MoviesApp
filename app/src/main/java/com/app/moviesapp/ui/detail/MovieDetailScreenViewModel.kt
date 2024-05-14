package com.app.moviesapp.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.moviesapp.data.WebService
import com.app.moviesapp.data.mapper.CastWidgetModel
import com.app.moviesapp.data.mapper.MovieWidgetModel
import com.app.moviesapp.data.mapper.toUIModel
import com.app.moviesapp.data.repository.MovieRepository
import com.app.moviesapp.data.ui_models.MovieDetailUIModel
import com.app.moviesapp.utils.ResultWrapper
import com.app.moviesapp.utils.ResultWrapperLocal
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
class MovieDetailScreenViewModel @Inject constructor(
    private val webService: WebService,
    private val movieRepository: MovieRepository,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    private val id = checkNotNull(savedStateHandle.get<String>("id"))

    private val _uiState = MutableStateFlow(MovieDetailUIStateModel())
    val uiState = _uiState.stateIn(viewModelScope, SharingStarted.WhileSubscribed(),
        MovieDetailUIStateModel()
    )
    init {
        getMovieDetails(id = id)
        getMovieCredits(id = id)
        getMovieRecommendations(id = id)
        getMovieSimilar(id = id)
        getMovieReviews(id = id)
        getImages(id = id)
        getVideos(movieId = id)
    }


    private fun getMovieDetails(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = movieRepository.getMovieDetails(id)) {
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
                            isLoading = false
                        )
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
                    response.value.cast.let { castList ->
                        _uiState.update { movieDetailUIStateModel ->
                            movieDetailUIStateModel.copy(
                                movieCastData = castList.take(5).map { it.CastWidgetModel() },
                                successCount = _uiState.value.successCount.plus(1),
                                isLoading = false,
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
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    private fun getMovieRecommendations(id: String) {
        viewModelScope.launch {
            when (val response = safeApiCall(Dispatchers.IO) {
                webService.getRecommendations(id, 1)
            }) {
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
                            isLoading = false
                        )
                    }
                }

                is ResultWrapper.Success -> {
                    _uiState.update {
                        it.copy(
                            movieRecommendations = response.value.results.map { it.MovieWidgetModel() },
                            isLoading = false,
                            successCount = _uiState.value.successCount.plus(1)
                        )
                    }
                }
            }
        }
    }

    private fun getMovieSimilar(id: String) {
        viewModelScope.launch {
            when (val response = safeApiCall(Dispatchers.IO) {
                webService.getSimilar(id, 1)
            }) {
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
                            isLoading = false
                        )
                    }
                }

                is ResultWrapper.Success -> {
                    _uiState.update {
                        it.copy(
                            movieSimilar = response.value.results.map { it.MovieWidgetModel() },
                            isLoading = false,
                            successCount = _uiState.value.successCount.plus(1)
                        )
                    }
                }
            }
        }
    }

    private fun getMovieReviews(id: String) {
        viewModelScope.launch {
            when (val response = safeApiCall(Dispatchers.IO) {
                webService.getReviews(id, 1)
            }) {
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
                            isLoading = false
                        )
                    }
                }

                is ResultWrapper.Success -> {
                    _uiState.update {
                        it.copy(
                            movieReviews = response.value.results.map { review -> review.toUIModel() },
                            isLoading = false,
                            successCount = _uiState.value.successCount.plus(1)
                        )
                    }
                }
            }
        }
    }

    private fun getImages(id: String) {
        viewModelScope.launch() {
            when (val response = safeApiCall(Dispatchers.IO) {
                webService.getImages(id)
            }) {
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
                            isLoading = false
                        )
                    }
                }

                is ResultWrapper.Success -> {
                    val images = response.value.posters.filter { it.iso_639_1 == "en" }.take(10)

                    _uiState.update {
                        it.copy(
                            images = images.map { it.getImagePath() },
                            isLoading = false,
                            successCount = _uiState.value.successCount.plus(1)
                        )
                    }
                }
            }
        }
    }
     fun addMovieToFavorite(movie : MovieDetailUIModel) {
        viewModelScope.launch(Dispatchers.IO) {
            when(val result = movieRepository.addMovieToFavorite(movie)) {
                is ResultWrapperLocal.Error -> {}
                is ResultWrapperLocal.Success -> {
                    _uiState.value = _uiState.value.copy(movieDetailData = result.value)
                }
            }
        }
    }

     fun removeMovieFromFavorite(movie : MovieDetailUIModel) {
        viewModelScope.launch(Dispatchers.IO) {
            when(val result = movieRepository.removeMovieFromFavorite(movie)) {
                is ResultWrapperLocal.Error -> {
                }
                is ResultWrapperLocal.Success -> {
                    _uiState.value = _uiState.value.copy(movieDetailData = result.value)                }
            }
        }
    }

    private fun getVideos(movieId: String) {
        viewModelScope.launch() {
            when (val response = movieRepository.getVideos(movieId)
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

