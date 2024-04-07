package com.app.moviesapp.ui.favorite.screens.movies


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.moviesapp.data.repository.MovieRepository
import com.app.moviesapp.utils.ResultWrapperLocal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteMoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val _favoriteMoviesState = MutableStateFlow(FavoriteMoviesUIStateModel())
    val uiState = _favoriteMoviesState.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(),
        FavoriteMoviesUIStateModel()
    )

    init {
        getFavoriteMovies()
    }

    private fun getFavoriteMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.getFavoriteMovies().collect {
                when (it) {
                    is ResultWrapperLocal.Error -> {}
                    is ResultWrapperLocal.Success -> {
                        _favoriteMoviesState.value =
                            _favoriteMoviesState.value.copy(favMovies = it.value)
                    }
                }
            }
        }
    }

}