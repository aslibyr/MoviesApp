package com.app.moviesapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.moviesapp.data.local.entity.FavoritePersonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritePersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoritePerson(personEntity: FavoritePersonEntity)

    @Query("SELECT * FROM favorite_persons WHERE personId = :personId")
    fun getFavoritePerson(personId: String): FavoritePersonEntity?

    @Query("SELECT * FROM favorite_persons")
    fun getFavoritePersons(): Flow<List<FavoritePersonEntity>>

    @Query("DELETE FROM favorite_persons Where personId = :personId")
    fun removeFavoritePerson(personId: Int)
}