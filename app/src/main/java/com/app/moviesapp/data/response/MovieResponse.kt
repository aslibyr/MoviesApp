package com.app.moviesapp.data.response

import android.os.Parcelable
import com.app.moviesapp.utils.Constant
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
    val adult: Boolean,
    val backdrop_path: String? = null,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Float,
    val poster_path: String? = null,
    val release_date: String? = null,
    val title: String,
    val video: Boolean,
    val vote_average: String? = null,
    val vote_count: Int
) : Parcelable {
    fun getImagePath(): String {
        return Constant.BASE_POSTER_URL + poster_path
    }
}
