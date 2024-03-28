package com.app.moviesapp.data.response

import android.os.Parcelable
import com.app.moviesapp.utils.Constant
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

data class MovieCreditResponse(
	@Json(name="id")
	val id: Int? = null,

	@Json(name="cast")
	val cast: List<MovieCreditResponseItem> = emptyList(),

	)

@Parcelize
data class MovieCreditResponseItem(

	@Json(name="cast_id")
	val cast_id: Int? = null,

	@Json(name="character")
	val character: String? = null,

	@Json(name="gender")
	val gender: Int? = null,

	@Json(name="credit_id")
	val credit_id: String? = null,

	@Json(name="known_for_department")
	val known_for_department: String? = null,

	@Json(name="original_name")
	val original_name: String? = null,

	@Json(name="popularity")
	val popularity: Double? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="profile_path")
	val profile_path: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="adult")
	val adult: Boolean? = null,

	@Json(name="order")
	val order: Int? = null
) : Parcelable {
	fun getImagePath(): String {
		return Constant.BASE_POSTER_URL + profile_path
	}
}
