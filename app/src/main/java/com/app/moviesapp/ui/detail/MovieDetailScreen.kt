package com.app.moviesapp.ui.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.moviesapp.custom.mapper.CastWidgetModel
import com.app.moviesapp.custom.widget.CastWidget
import com.app.moviesapp.custom.widget.CastWidgetComponentModel
import com.app.moviesapp.custom.widget.MovieWidget
import com.app.moviesapp.custom.widget.MovieWidgetComponentModel
import com.app.moviesapp.custom.widget.MovieWidgetModel
import com.app.moviesapp.data.response.MovieDetailResponse
import com.app.moviesapp.ui.detail.components.MovieDetailPagerComponent
import com.app.moviesapp.ui.detail.components.MovieDetailsComponent


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailScreen(
    viewModel: ItemDetailScreenViewModel = hiltViewModel(), onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


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
                    images = uiState.images,
                    onBackClick = onBackClick,
                    castModel = CastWidgetComponentModel(
                        "Cast Ekibi",
                        cast = uiState.movieCastData.map { it.CastWidgetModel() }),
                    similar = uiState.movieSimilar,
                    recommendations = uiState.movieRecommendations
                )
            }
        } else {
            Text(text = "error")
        }
    }
}

@Composable
fun MovieDetailUI(
    similar: List<MovieWidgetModel>,
    recommendations: List<MovieWidgetModel>,
    movie: MovieDetailResponse,
    images: List<String>,
    castModel: CastWidgetComponentModel,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MovieDetailPagerComponent(images = images, onBackClick = onBackClick)
        MovieDetailsComponent(title = movie.title ?: "", overview = movie.overview ?: "")
        CastWidget(model = castModel) {

        }
        MovieWidget(
            model = MovieWidgetComponentModel(
                widgetCategory = "Recommendations",
                movies = recommendations
            ), openListScreen = { }) {

        }
        MovieWidget(
            model = MovieWidgetComponentModel(
                widgetCategory = "Similar",
                movies = similar
            ), openListScreen = { }) {

        }
    }
}



