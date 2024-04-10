package com.app.moviesapp.ui.favorite.screens.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.moviesapp.data.repository.PersonRepository
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
    private val _favoritePersonsState = MutableStateFlow(FavoritePeopleUIStateModel())
    val uiState = _favoritePersonsState.stateIn(
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
                        _favoritePersonsState.value =
                            _favoritePersonsState.value.copy(favPeople = it.value)
                    }
                }
            }
        }
    }

}