package com.app.moviesapp.data.repository

import com.app.moviesapp.data.local.MoviesAppDataBase
import com.app.moviesapp.data.mapper.toFavoritePersonEntity
import com.app.moviesapp.data.mapper.toUIModel
import com.app.moviesapp.data.ui_models.PersonUIModel
import com.app.moviesapp.utils.ResultWrapperLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PersonRepository @Inject constructor(
    private val appDataBase: MoviesAppDataBase
) {
    suspend fun addPersonToFavorite(person: PersonUIModel): ResultWrapperLocal<PersonUIModel> {
        return try {
            appDataBase.favoritePerson().insertFavoritePerson(person.toFavoritePersonEntity())
            val newPersonData = PersonUIModel(
                personId = person.personId,
                name = person.name,
                profilePath = person.profilePath,
                isFavorite = true
            )
            ResultWrapperLocal.Success(newPersonData)
        } catch (e: Exception) {
            ResultWrapperLocal.Error(e.message ?: "")
        }
    }

    fun removePersonFromFavorite(person: PersonUIModel): ResultWrapperLocal<PersonUIModel> {
        return try {
            appDataBase.favoritePerson().removeFavoritePerson(person.personId)
            val newPersonDetailData = PersonUIModel(
                personId = person.personId,
                name = person.name,
                profilePath = person.profilePath,
                isFavorite = false
            )
            ResultWrapperLocal.Success(newPersonDetailData)
        } catch (e: Exception) {
            ResultWrapperLocal.Error(e.message ?: "")
        }
    }

    fun isFavorite(personId: String): Boolean {
        return appDataBase.favoritePerson().getFavoritePerson(personId) != null
    }

    fun getFavoritePersons(): Flow<ResultWrapperLocal<List<PersonUIModel>>> {
        return flow {
            try {
                val favoritePerson = appDataBase.favoritePerson().getFavoritePersons().map {
                    it.toUIModel()
                }
                emit(ResultWrapperLocal.Success(favoritePerson))
            } catch (e: Exception) {
                emit(ResultWrapperLocal.Error(e.message ?: ""))
            }
        }
    }
}