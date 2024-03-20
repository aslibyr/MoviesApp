package com.app.moviesapp.data.response

import com.squareup.moshi.Json

data class MovieReviewsResponse(

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "page")
    val page: Int? = null,

    @Json(name = "total_pages")
    val totalPages: Int? = null,

    @Json(name = "results")
    val results: List<ReviewResultsItem?>? = null,

    @Json(name = "total_results")
    val totalResults: Int? = null
)

data class AuthorDetails(

    @Json(name = "avatar_path")
    val avatarPath: Any? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "rating")
    val rating: Int? = null,

    @Json(name = "username")
    val username: String? = null
)

data class ReviewResultsItem(

    @Json(name = "author_details")
    val authorDetails: AuthorDetails? = null,

    @Json(name = "updated_at")
    val updatedAt: String? = null,

    @Json(name = "author")
    val author: String? = null,

    @Json(name = "created_at")
    val createdAt: String? = null,

    @Json(name = "id")
    val id: String? = null,

    @Json(name = "content")
    val content: String? = null,

    @Json(name = "url")
    val url: String? = null
)
