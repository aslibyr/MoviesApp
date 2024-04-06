package com.app.moviesapp.ui.detail.screens.person

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.app.moviesapp.custom.indicator.PagerIndicator
import com.app.moviesapp.custom.popup.CustomImagePopUp
import com.app.moviesapp.data.ui_models.PersonUIModel


@Composable
fun PersonScreen(viewModel: PersonScreenViewModel = hiltViewModel(), onBackClick: () -> Unit = {}) {
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
        PersonScreenUI(
            person = uiState.personData,
            images = uiState.images,
            isFavorite = uiState.personData.isFavorite,
            onFavoriteClicked = { isFavorite ->
                if (isFavorite) {
                    viewModel.removePersonFromFavorite(uiState.personData)
                } else {
                    viewModel.addPersonToFavorite(uiState.personData)
                }
            })
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PersonScreenUI(
    person: PersonUIModel,
    images: List<String>,
    isFavorite: Boolean,
    onFavoriteClicked: (Boolean) -> Unit
) {
    val pagerState = rememberPagerState {
        images.size
    }
    var showImagePopup by remember {
        mutableStateOf(false)
    }
    if (showImagePopup) {
        CustomImagePopUp(image = images[pagerState.currentPage]) {
            showImagePopup = false
        }
    }
    Column(
        modifier = Modifier
            .blur(if (showImagePopup) 10.dp else 0.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Box(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .padding(bottom = 8.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            HorizontalPager(state = pagerState) { page ->
                val currentImage = images.getOrNull(page)
                if (currentImage != null) {
                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showImagePopup = true
                            },
                        model = currentImage,
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth,
                        loading = {
                            CircularProgressIndicator()
                        }
                    )
                }
            }

            val favoriteIcon =
                if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
            Icon(imageVector = favoriteIcon,
                "",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(38.dp)
                    .padding(end = 16.dp, top = 16.dp)
                    .clickable {
                        onFavoriteClicked(isFavorite)
                    }
                    .shadow(50.dp),
                tint = Color.White)
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
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
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = person.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = person.biography,
                fontStyle = FontStyle.Italic,
                color = Color.White
            )
        }
    }
}


