package com.app.moviesapp.custom.indicator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    modifier: Modifier
) {

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp)

    ) {

        items(totalDots) { index ->
            val color = if (index == selectedIndex) Color.White else Color.White.copy(alpha = 0.5f)
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(color = color)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(color)
                )
            }
        }
    }
}