package com.app.moviesapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("favorite_persons")
data class FavoritePersonEntity(
    @PrimaryKey
    val name: String = "",
    val profilePath: String = "",
    val personId: String = ""
)
