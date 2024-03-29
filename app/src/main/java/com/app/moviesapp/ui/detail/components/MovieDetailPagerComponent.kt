package com.app.moviesapp.ui.detail.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.app.moviesapp.custom.indicator.PagerIndicator

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieDetailPagerComponent(images: List<String>, onBackClick: () -> Unit) {
    val pagerState = rememberPagerState {
        images.size
    }
    Box(modifier = Modifier.height(500.dp)) {
        HorizontalPager(state = pagerState) { page ->
            val currentImage = images.getOrNull(page)
            if (currentImage != null) {
                SubcomposeAsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = currentImage,
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth,
                    loading = {
                        CircularProgressIndicator()
                    }
                )
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp)
        ) {
            PagerIndicator(
                pagerState = pagerState, indicatorSize = 8.dp,
                indicatorCount = 7,
                indicatorShape = CircleShape
            )
        }

        Icon(imageVector = Icons.Filled.NavigateBefore,
            "",
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(38.dp)
                .padding(start = 16.dp, top = 16.dp)
                .clickable {
                    onBackClick()
                }
                .shadow(50.dp),
            tint = Color.White)
    }
}