package com.app.moviesapp.data.mapper

import com.app.moviesapp.data.response.ReviewResultsItem
import com.app.moviesapp.data.ui_models.MovieReviewsUIModel

fun ReviewResultsItem.toUIModel() : MovieReviewsUIModel {
    return MovieReviewsUIModel(
        author = this.author ?: "",
        review = this.content ?: "",
        authorImage = this.author_details?.getImagePath() ?: "",
        date = this.created_at ?: "",
        rating = this.author_details?.rating.toString()
    )
}