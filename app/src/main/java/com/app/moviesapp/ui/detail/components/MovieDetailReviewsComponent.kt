package com.app.moviesapp.ui.detail.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.app.moviesapp.ui.detail.ui_models.MovieReviewsUIModel

@Composable
fun MovieDetailReviewsComponent(
    reviews: List<MovieReviewsUIModel>
) {

    AnimatedVisibility(visible = reviews.isNotEmpty()) {
        Column(Modifier.fillMaxWidth()) {
            reviews.forEach { review ->
                MovieReviewItem(review = review)
            }
        }
    }
}


@Composable
fun MovieReviewItem(
    review: MovieReviewsUIModel,
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { isExpanded = !isExpanded },
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = review.author, fontWeight = FontWeight.Medium)
            if (!review.rating.contains("null")) {
                Text(text = review.rating)
            }
        }

        Text(
            text = review.review,
            maxLines = if (isExpanded) Int.MAX_VALUE else 3,
            overflow = TextOverflow.Ellipsis
        )
        if (!isExpanded) {
            Text(
                text = "Daha fazla göster",
                modifier = Modifier.clickable { isExpanded = true },
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }
    }
}