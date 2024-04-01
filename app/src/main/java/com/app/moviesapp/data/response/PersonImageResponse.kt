package com.app.moviesapp.data.response

import android.os.Parcelable
import com.app.moviesapp.utils.Constant
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

data class PersonImageResponse(

	@Json(name = "profiles")
	val profiles: List<ProfilesItem?>? = null,

	@Json(name = "id")
	val id: Int? = null
)

@Parcelize
data class ProfilesItem(

	@Json(name = "aspect_ratio")
	val aspect_ratio: Double? = null,

	@Json(name = "file_path")
	val file_path: String? = null,

	@Json(name = "vote_average")
	val vote_average: Double? = null,

	@Json(name = "width")
	val width: Int? = null,

	@Json(name = "iso_639_1")
	val iso_639_1: String? = null,

	@Json(name = "vote_count")
	val vote_count: Int? = null,

	@Json(name = "height")
	val height: Int? = null
) : Parcelable {
	fun getImagePath(): String {
		return Constant.BASE_POSTER_URL + file_path
	}
}
