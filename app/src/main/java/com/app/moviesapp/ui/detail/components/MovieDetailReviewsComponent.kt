package com.app.moviesapp.ui.detail.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.app.moviesapp.data.ui_models.MovieReviewsUIModel

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
                .fillMaxWidth()
                .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = review.author,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )

            if (!review.rating.contains("null")) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Rating",
                        modifier = Modifier.size(16.dp),
                        tint = Color.White
                    )
                    Text(text = review.rating, Modifier.padding(start = 4.dp))
                }
            }

        }
        if (!review.review.contains("null") || !review.review.contains("<") || !review.review.contains(
                ">"
            ) || !review.review.contains("/") || !review.review.contains("*")
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .padding(bottom = 6.dp),
                text = review.review,
                maxLines = if (isExpanded) Int.MAX_VALUE else 3,
                overflow = TextOverflow.Ellipsis
            )
        }

        if (!isExpanded) {
            Text(
                text = "Show more",
                modifier = Modifier
                    .padding(6.dp)
                    .clickable { isExpanded = true },
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
