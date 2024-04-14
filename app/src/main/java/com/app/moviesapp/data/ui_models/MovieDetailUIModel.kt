package com.app.moviesapp.data.ui_models

import android.os.Parcelable
import com.app.moviesapp.utils.Constant
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetailUIModel(
    val title: String = "",
    val overview: String = "",
    val movieId: String = "",
    val duration: String = "",
    val isFavorite: Boolean = false,
    val movieImage: String = ""
) : Parcelable {
    fun getImagePath(): String {
        return Constant.BASE_POSTER_URL + movieImage
    }
}


