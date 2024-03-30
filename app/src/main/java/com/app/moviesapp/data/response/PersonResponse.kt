package com.app.moviesapp.data.response

import android.os.Parcelable
import com.app.moviesapp.utils.Constant
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonResponse(

    @Json(name = "also_known_as")
    val also_known_as: List<String?>? = null,

    @Json(name = "birthday")
    val birthday: String? = null,

    @Json(name = "gender")
    val gender: Int? = null,

    @Json(name = "imdb_id")
    val imdb_id: String? = null,

    @Json(name = "known_for_department")
    val known_for_department: String? = null,

    @Json(name = "profile_path")
    val profile_path: String? = null,

    @Json(name = "biography")
    val biography: String? = null,

    @Json(name = "deathday")
    val deathday: String? = null,

    @Json(name = "place_of_birth")
    val place_of_birth: String? = null,

    @Json(name = "popularity")
    val popularity: Double? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "adult")
    val adult: Boolean? = null,

    @Json(name = "homepage")
    val homepage: String? = null
) : Parcelable {
    fun getImagePath(): String {
        return Constant.BASE_POSTER_URL + profile_path
    }
}