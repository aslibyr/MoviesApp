package com.app.moviesapp.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.moviesapp.custom.navigation.graphs.MovieListType
import com.app.moviesapp.custom.widget.CastWidget
import com.app.moviesapp.custom.widget.CastWidgetComponentModel
import com.app.moviesapp.custom.widget.MovieWidget
import com.app.moviesapp.custom.widget.MovieWidgetComponentModel
import com.app.moviesapp.custom.widget.MovieWidgetModel
import com.app.moviesapp.data.ui_models.MovieDetailUIModel
import com.app.moviesapp.data.ui_models.MovieReviewsUIModel
import com.app.moviesapp.ui.detail.components.MovieDetailPagerComponent
import com.app.moviesapp.ui.detail.components.MovieDetailReviewsComponent
import com.app.moviesapp.ui.detail.components.MovieDetailsComponent
import com.app.moviesapp.utils.Constant
import com.app.moviesapp.utils.ScreenRoutes


@Composable
fun DetailScreen(
    viewModel: MovieDetailScreenViewModel = hiltViewModel(), onBackClick: () -> Unit,
    openMovieListScreen: (String) -> Unit,
    openMovieDetail: (String) -> Unit,
    openCastScreen: (String) -> Unit,
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
            MovieDetailUI(
                movie = uiState.movieDetailData,
                images = uiState.images,
                onBackClick = onBackClick,
                castModel = CastWidgetComponentModel(
                    "Cast Ekibi",
                    cast = uiState.movieCastData
                ),
                similar = uiState.movieSimilar,
                recommendations = uiState.movieRecommendations,
                reviews = uiState.movieReviews,
                openMovieListScreen = openMovieListScreen,
                openMovieDetail = openMovieDetail,
                openCastScreen = openCastScreen
            )
        }
    }
}

@Composable
fun MovieDetailUI(
    similar: List<MovieWidgetModel>,
    recommendations: List<MovieWidgetModel>,
    movie: MovieDetailUIModel,
    images: List<String>,
    castModel: CastWidgetComponentModel,
    reviews: List<MovieReviewsUIModel>,
    onBackClick: () -> Unit,
    openMovieListScreen: (String) -> Unit,
    openMovieDetail: (String) -> Unit,
    openCastScreen: (String) -> Unit,

    ) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MovieDetailPagerComponent(images = images, onBackClick = onBackClick)
        MovieDetailsComponent(
            title = movie.title,
            overview = movie.overview,
            duration = movie.duration
        )
        CastWidget(model = castModel, openCastListScreen = {
            val route = ScreenRoutes.MOVIE_CAST_ROUTE.replace(
                oldValue = "{movie_id}",
                newValue = movie.movieId
            )
            openCastScreen(route)
        })


        if (recommendations.isNotEmpty()) {
            MovieWidget(
                model = MovieWidgetComponentModel(
                    widgetCategory = "Recommendations",
                    movies = recommendations
                ), openListScreen = {
                    val route = ScreenRoutes.HOME_LIST_ROUTE.replace(
                        oldValue = Constant.TYPE,
                        newValue = MovieListType.RECOMMENDATIONS.type
                    ).replace(
                        oldValue = "{movie_id}",
                        newValue = movie.movieId
                    )
                    openMovieListScreen(route)
                },
                onMovieClick = { route ->
                    openMovieDetail(route)
                })
        }
        if (similar.isNotEmpty()) {
            MovieWidget(
                model = MovieWidgetComponentModel(
                    widgetCategory = "Similar",
                    movies = similar
                ), openListScreen = {
                    val route = ScreenRoutes.HOME_LIST_ROUTE.replace(
                        oldValue = Constant.TYPE,
                        newValue = MovieListType.SIMILAR.type
                    ).replace(
                        oldValue = "{movie_id}",
                        newValue = movie.movieId
                    )
                    openMovieListScreen(route)
                },
                onMovieClick = { route ->

                    openMovieDetail(route)
                })
        }
        MovieDetailReviewsComponent(reviews = reviews)
    }
}



