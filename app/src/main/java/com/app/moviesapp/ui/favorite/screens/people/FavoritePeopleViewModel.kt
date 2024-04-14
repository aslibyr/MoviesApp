package com.app.moviesapp.ui.favorite.screens.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.moviesapp.data.repository.PersonRepository
import com.app.moviesapp.data.ui_models.PersonUIModel
import com.app.moviesapp.utils.ResultWrapperLocal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritePeopleViewModel @Inject constructor(
    private val personRepository: PersonRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(FavoritePeopleUIStateModel())
    val uiState = _uiState.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(),
        FavoritePeopleUIStateModel()
    )

    init {
        getFavoritePerson()
    }

    private fun getFavoritePerson() {
        viewModelScope.launch(Dispatchers.IO) {
            personRepository.getFavoritePersons().collect {
                when (it) {
                    is ResultWrapperLocal.Error -> {}
                    is ResultWrapperLocal.Success -> {
                        _uiState.value =
                            _uiState.value.copy(favPeople = it.value)
                    }
                }
            }
        }
    }

    fun removeFromFavoritePerson(model: PersonUIModel) {
        viewModelScope.launch(Dispatchers.IO) {
            when (personRepository.removePersonFromFavorite(model)) {
                is ResultWrapperLocal.Error -> {}
                is ResultWrapperLocal.Success -> {
                    _uiState.value = _uiState.value.copy(isRemoved = true)
                }
            }
        }
    }

    fun toastMessageShowed() {
        _uiState.value = _uiState.value.copy(isRemoved = false)
    }
}