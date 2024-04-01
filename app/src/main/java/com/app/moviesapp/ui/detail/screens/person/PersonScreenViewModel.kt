package com.app.moviesapp.ui.detail.screens.person

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.moviesapp.data.WebService
import com.app.moviesapp.data.mapper.PersonUIModel
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
class PersonScreenViewModel @Inject constructor(
    private val webService: WebService,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow(PersonUIStateModel())
    val uiState = _uiState.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(),
        PersonUIStateModel()
    )
    private val personId = checkNotNull(savedStateHandle.get<String>("person_id"))

    init {
        getPerson(personId)
        getPersonImages(personId)
    }

    private fun getPerson(personId: String) {
        viewModelScope.launch {
            when (val response = safeApiCall(Dispatchers.IO) {
                webService.getPerson(personId)
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
                            personData = response.value.PersonUIModel(),
                            isLoading = false,
                            isSuccess = true
                        )
                    }
                }
            }
        }
    }

    private fun getPersonImages(id: String) {
        viewModelScope.launch {
            when (val response = safeApiCall(Dispatchers.IO) {
                webService.getPersonImages(id)
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
                    val images =
                        response.value.profiles?.mapNotNull { it?.getImagePath() }?.take(10)

                    if (images != null) {
                        _uiState.update {
                            it.copy(
                                images = images,
                                isLoading = false,
                            )
                        }
                    }
                }
            }
        }
    }
}