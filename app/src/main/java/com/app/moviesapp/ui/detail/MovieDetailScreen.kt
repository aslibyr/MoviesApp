package com.app.moviesapp.ui.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.app.moviesapp.custom.indicator.PagerIndicator
import com.app.moviesapp.data.response.MovieDetailResponse


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailScreen(
    viewModel: ItemDetailScreenViewModel = hiltViewModel(), onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState {
        uiState.images.size
    }

    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        if (uiState.successCount >= 6) {
            uiState.movieDetailData?.let { movie ->
                MovieDetailUI(
                    movie = movie,
                    image = uiState.images,
                    onBackClick = onBackClick,
                    pagerState = pagerState
                )
            }
        } else {
            Text(text = "error")
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieDetailUI(
    movie: MovieDetailResponse,
    image: List<String>,
    pagerState: PagerState,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box {
            HorizontalPager(state = pagerState) { page ->
                val currentImage = image.getOrNull(page)
                if (currentImage != null) {

                    AsyncImage(
                        modifier = Modifier.fillMaxWidth(),
                        model = currentImage,
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth
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
        movie.title?.let {
            Text(
                fontSize = (24.sp),
                text = it,
                fontWeight = FontWeight.Bold
            )
        }
        movie.overview?.let { Text(text = it, fontSize = 14.sp) }
    }
}



