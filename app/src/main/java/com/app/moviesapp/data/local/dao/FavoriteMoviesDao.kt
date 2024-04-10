package com.app.moviesapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.moviesapp.data.local.entity.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(movieEntity: FavoriteMovieEntity)

    @Query("SELECT * FROM favorite_movies WHERE movieId = :movieId")
    fun getFavoriteMovie(movieId: String): FavoriteMovieEntity?

    @Query("SELECT * FROM favorite_movies")
    fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>>

    @Query("DELETE FROM favorite_movies Where movieId = :movieId")
    fun removeFavoriteMovie(movieId: String)
}