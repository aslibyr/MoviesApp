package com.app.moviesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.moviesapp.data.local.dao.FavoriteMoviesDao
import com.app.moviesapp.data.local.entity.FavoriteMovieEntity


@Database(entities = [FavoriteMovieEntity::class], version = 1)
abstract class MoviesAppDataBase : RoomDatabase() {
    abstract fun favoriteMovies(): FavoriteMoviesDao
}