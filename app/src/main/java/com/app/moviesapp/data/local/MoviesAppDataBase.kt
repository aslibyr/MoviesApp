package com.app.moviesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.moviesapp.data.local.dao.FavoriteMoviesDao
import com.app.moviesapp.data.local.dao.FavoritePersonDao
import com.app.moviesapp.data.local.entity.FavoriteMovieEntity
import com.app.moviesapp.data.local.entity.FavoritePersonEntity


@Database(entities = [FavoriteMovieEntity::class, FavoritePersonEntity::class], version = 2)
abstract class MoviesAppDataBase : RoomDatabase() {
    abstract fun favoriteMovies(): FavoriteMoviesDao
    abstract fun favoritePerson(): FavoritePersonDao
}