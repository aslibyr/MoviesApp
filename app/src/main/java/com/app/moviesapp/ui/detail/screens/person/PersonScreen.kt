package com.app.moviesapp.ui.detail.screens.person

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.app.moviesapp.custom.indicator.PagerIndicator
import com.app.moviesapp.data.ui_models.PersonUIModel


@Composable
fun PersonScreen(viewModel: PersonScreenViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    if (uiState.isError) {
        Toast.makeText(context, "Network error", Toast.LENGTH_LONG).show()
    }
    if (uiState.isSuccess) {
        PersonScreenUI(person = uiState.personData, image = uiState.images)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PersonScreenUI(person: PersonUIModel, image: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val pagerState = rememberPagerState {
            image.size
        }
        Box(
            modifier = Modifier
                .height(500.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            HorizontalPager(state = pagerState) { page ->
                val currentImage = image.getOrNull(page)
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
                    .padding(bottom = 24.dp)
            ) {
                PagerIndicator(
                    pagerState = pagerState, indicatorSize = 8.dp,
                    indicatorCount = 7,
                    indicatorShape = CircleShape
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue)
        ) {
            Text(
                text = person.name,
                fontSize = 16.sp,
                color = Color.White
            )
            Text(
                text = person.biography,
                color = Color.White
            )
        }
    }
}


