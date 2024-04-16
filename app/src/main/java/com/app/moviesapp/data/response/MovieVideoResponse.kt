package com.app.moviesapp.data.response

import com.squareup.moshi.Json

data class MovieVideoResponse(

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "results")
    val results: List<ResultsItem> = emptyList()
)

data class ResultsItem(

    @Json(name = "site")
    val site: String? = null,

    @Json(name = "size")
    val size: Int? = null,

    @Json(name = "iso_3166_1")
    val iso_3166_1: String? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "official")
    val official: Boolean? = null,

    @Json(name = "id")
    val id: String? = null,

    @Json(name = "type")
    val type: String? = null,

    @Json(name = "published_at")
    val published_at: String? = null,

    @Json(name = "iso_639_1")
    val iso_639_1: String? = null,

    @Json(name = "key")
    val key: String? = null
)
