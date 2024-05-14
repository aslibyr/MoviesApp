package com.app.moviesapp.ui.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.moviesapp.data.ui_models.MovieDetailUIModel

@Composable
fun MovieDetailsComponent(
    title: String,
    overview: String,
    duration: String,
    movie: MovieDetailUIModel,
    releaseDate: String,
) {
    Column(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            fontSize = (24.sp),
            text = title,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = releaseDate.take(4),
            fontSize = 14.sp,
            fontWeight = FontWeight.Light
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.AccessTime,
                contentDescription = "",
                modifier = Modifier.size(16.dp)
            )
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = duration,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Rating",
                modifier = Modifier
                    .size(16.dp)
                    .shadow(4.dp, shape = CircleShape),
                tint = Color.White
            )
            Text(
                text = movie.voteAvg.take(3),
                modifier = Modifier
                    .padding(start = 4.dp)
                    .shadow(4.dp),
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
            )
        }
        Text(
            text = overview,
            fontSize = 14.sp
        )
    }
}