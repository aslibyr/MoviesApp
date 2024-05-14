package com.app.moviesapp.data.response

import com.squareup.moshi.Json

data class MovieDetailResponse(

	@Json(name="original_language")
	val original_language: String? = null,

	@Json(name="imdb_id")
	val imdb_id: String? = null,

	@Json(name="video")
	val video: Boolean? = null,

	@Json(name="title")
	val title: String? = null,

	@Json(name="backdrop_path")
	val backdrop_path: String? = null,

	@Json(name="revenue")
	val revenue: Int? = null,

	@Json(name="genres")
	val genres: List<GenresItem?>? = null,

	@Json(name="popularity")
	val popularity: Double? = null,

	@Json(name="production_countries")
	val production_countries: List<ProductionCountriesItem?>? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="vote_count")
	val vote_count: Int? = null,

	@Json(name="budget")
	val budget: Int? = null,

	@Json(name="overview")
	val overview: String? = null,

	@Json(name="original_title")
	val original_title: String? = null,

	@Json(name="runtime")
	val runtime: Int? = null,

	@Json(name="poster_path")
	val poster_path: String? = null,

	@Json(name="spoken_languages")
	val spokenLanguages: List<SpokenLanguagesItem?>? = null,

	@Json(name="production_companies")
	val production_companies: List<ProductionCompaniesItem?>? = null,

	@Json(name="release_date")
	val release_date: String? = null,

	@Json(name="vote_average")
    val vote_average: String? = null,

	@Json(name="belongs_to_collection")
	val belongs_to_collection: BelongsToCollection? = null,

	@Json(name="tagline")
	val tagline: String? = null,

	@Json(name="adult")
	val adult: Boolean? = null,

	@Json(name="homepage")
	val homepage: String? = null,

	@Json(name="status")
	val status: String? = null
)

data class SpokenLanguagesItem(

	@Json(name="name")
	val name: String? = null,

	@Json(name="iso_639_1")
	val iso6391: String? = null,

	@Json(name="english_name")
	val english_name: String? = null
)

data class BelongsToCollection(

	@Json(name="backdrop_path")
	val backdrop_path: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="poster_path")
	val poster_path: String? = null
)

data class ProductionCompaniesItem(

	@Json(name="logo_path")
	val logo_path: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="origin_country")
	val origin_country: String? = null
)

data class ProductionCountriesItem(

	@Json(name="iso_3166_1")
	val iso_3166_1: String? = null,

	@Json(name="name")
	val name: String? = null
)

data class GenresItem(

	@Json(name="name")
	val name: String? = null,

	@Json(name="id")
	val id: Int? = null
)
