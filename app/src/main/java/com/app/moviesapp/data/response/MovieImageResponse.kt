package com.app.moviesapp.data.response

import android.os.Parcelable
import com.app.moviesapp.utils.Constant
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

data class MovieImageResponse(

	@Json(name = "backdrops")
	val backdrops: List<ImagesItem?>? = null,

	@Json(name = "posters")
	val posters: List<ImagesItem>,

	@Json(name = "id")
	val id: Int? = null,

	@Json(name = "logos")
	val logos: List<ImagesItem?>? = null
)

@Parcelize
data class ImagesItem(

	@Json(name = "aspect_ratio")
	val aspect_ratio: Double?,

	@Json(name = "file_path")
	val file_path: String,

	@Json(name = "vote_average")
	val vote_average: Double?,

	@Json(name = "width")
	val width: Int?,

	@Json(name = "iso_639_1")
	val iso_639_1: String?,

	@Json(name = "vote_count")
	val vote_count: Int?,

	@Json(name = "height")
	val height: Int?,

	) : Parcelable {
	fun getImagePath(): String {
		return Constant.BASE_POSTER_URL + file_path
	}
}



