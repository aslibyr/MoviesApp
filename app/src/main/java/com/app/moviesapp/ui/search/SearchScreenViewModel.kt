package com.app.moviesapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val webService: WebService
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUIStateModel())
    val uiState : StateFlow<SearchUIStateModel> get() = _uiState.stateIn(viewModelScope,
        SharingStarted.Eagerly, SearchUIStateModel()
    )


    fun searchQueryChanged(searchQuery: String) {
        _uiState.update {
            it.copy(
                searchQuery = searchQuery
            )
        }
    }
    fun searchMovie(query : String,page :Int) {
        viewModelScope.launch {

            when(val response = safeApiCall(Dispatchers.IO) {webService.getSearchMovieApi(
                page = page,
                query = query
            )}){
                is ResultWrapper.GenericError -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                        )
                    }
                }
                ResultWrapper.Loading -> {
                    _uiState.update {
                        it.copy(
                            isLoading = true,
                        )
                    }
                }
                ResultWrapper.NetworkError -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                        )
                    }
                }
                is ResultWrapper.Success -> {
                    val moviesList : MutableList<MovieResponse> = _uiState.value.movies.toMutableList()
                    moviesList.addAll(response.value.results)
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            movies = moviesList,
                            page = response.value.page,
                            totalPages = response.value.total_pages
                        )
                    }
                }
            }

        }
    }

}

data class SearchUIStateModel(
    val movies : List<MovieResponse> = emptyList(),
    val isLoading : Boolean = false,
    val searchQuery : String = "",
    val page : Int = 1,
    val totalPages : Int = 0
)