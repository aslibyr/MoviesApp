package com.app.moviesapp.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity("favorite_movies")
data class FavoriteMovieEntity(
    @PrimaryKey
    val movieId: String = "",
    val title: String = "",
    val overview: String = "",
    val duration: String = "",
    val movieImage: String = "",
    val voteAvg: String = "",
    val releaseDate: String = ""
) : Parcelable

