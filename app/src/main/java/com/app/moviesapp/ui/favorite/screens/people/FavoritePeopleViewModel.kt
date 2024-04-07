package com.app.moviesapp.ui.favorite.screens.people

import androidx.lifecycle.ViewModel
import com.app.moviesapp.data.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritePeopleViewModel @Inject constructor(
    private val personRepository: PersonRepository
) : ViewModel() {

}