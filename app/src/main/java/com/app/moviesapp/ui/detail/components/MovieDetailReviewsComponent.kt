package com.app.moviesapp.ui.detail.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.moviesapp.ui.detail.ui_models.MovieReviewsUIModel

@Composable
fun MovieDetailReviewsComponent(
    reviews : List<MovieReviewsUIModel>
) {

    AnimatedVisibility(visible = reviews.isNotEmpty()) {
        Column(Modifier.fillMaxWidth()) {
            reviews.forEach {review ->
                MovieReviewItem(review = review)
            }
        }
    }

}


@Composable
fun MovieReviewItem(
    review : MovieReviewsUIModel
) {

    Column {

        Text(text = review.author)
        Text(text = review.review)
        Text(text = review.rating)
    }
}