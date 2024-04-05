package com.app.moviesapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("favorite_movies")
data class FavoriteMovieEntity(
    @PrimaryKey
    val movieId: String = "",
    val title: String = "",
    val overview: String = "",
    val duration: String = ""
)
