package com.app.moviesapp.ui.detail.screens

import com.app.moviesapp.data.response.ReviewResultsItem

data class MovieReviewsUIModel(
    val author : String = "",
    val review : String = "",
    val authorImage : String = "",
    val date : String = "",
    val rating : String = ""
)

fun ReviewResultsItem.toUIModel() : MovieReviewsUIModel {
    return MovieReviewsUIModel(
        author = this.author ?: "",
        review = this.content ?: "",
        authorImage = this.author_details?.getImagePath() ?: "",
        date = this.created_at ?: "",
        rating = this.author_details?.rating.toString()
    )
}


