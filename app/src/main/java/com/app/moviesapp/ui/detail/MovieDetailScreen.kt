package com.app.moviesapp.ui.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.moviesapp.custom.image.MoviesImageView
import com.app.moviesapp.custom.tab.CustomTab
import com.app.moviesapp.custom.tab.getTabList
import com.app.moviesapp.custom.widget.CastWidget
import com.app.moviesapp.custom.widget.CastWidgetComponentModel
import com.app.moviesapp.custom.widget.MovieWidgetModel
import com.app.moviesapp.data.ui_models.MovieDetailUIModel
import com.app.moviesapp.data.ui_models.MovieReviewsUIModel
import com.app.moviesapp.data.ui_models.MovieVideoUIModel
import com.app.moviesapp.ui.detail.components.MovieDetailPagerComponent
import com.app.moviesapp.ui.detail.components.MovieDetailReviewsComponent
import com.app.moviesapp.ui.detail.components.MovieDetailsComponent
import com.app.moviesapp.ui.detail.screens.videos.MovieDetailVideoScreen
import com.app.moviesapp.ui.favorite.TabItemModel
import com.app.moviesapp.utils.theme.Pink40


@Composable
fun DetailScreen(
    viewModel: MovieDetailScreenViewModel = hiltViewModel(), onBackClick: () -> Unit,
    openMovieDetail: (String) -> Unit,
    openCastScreen: (String) -> Unit,
    openPersonScreen: (String) -> Unit
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
                openMovieDetail = openMovieDetail,
                openCastScreen = openCastScreen,
                openPersonScreen = openPersonScreen,
                onFavoriteClicked = { isFavorite ->
                    if (isFavorite) {
                        viewModel.removeMovieFromFavorite(uiState.movieDetailData)
                    } else {
                        viewModel.addMovieToFavorite(uiState.movieDetailData)
                    }
                },
                videos = uiState.videoData
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
    openMovieDetail: (String) -> Unit,
    openCastScreen: (String) -> Unit,
    openPersonScreen: (String) -> Unit,
    onFavoriteClicked: (Boolean) -> Unit,
    videos: List<MovieVideoUIModel>

) {
    var tabIndex by remember {
        mutableIntStateOf(0)
    }
    val tabItems: List<TabItemModel> = getTabList()
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MovieDetailPagerComponent(
            images = images,
            onBackClick = onBackClick,
            isFavorite = movie.isFavorite,
            onFavoriteClicked = onFavoriteClicked
        )
        MovieDetailsComponent(
            title = movie.title,
            overview = movie.overview,
            duration = movie.duration
        )
        CastWidget(
            model = castModel, openCastListScreen = {
                openCastScreen(movie.movieId)
            }, openPersonScreen = openPersonScreen
        )
        MovieDetailReviewsComponent(reviews = reviews)

        ScrollableTabRow(selectedTabIndex = tabIndex, divider = {},
            indicator = {
                Divider(
                    modifier = Modifier
                        .tabIndicatorOffset(it[tabIndex])
                        .border(BorderStroke(2.dp, Pink40))
                )
            }) {
            tabItems.forEachIndexed { index, item ->
                CustomTab(onClick = {
                    tabIndex = index
                }, text = item.title, modifier = Modifier)
            }
        }
        when (tabIndex) {
            0 -> {
                TabListContent(movies = recommendations, openMovieDetail = openMovieDetail)
            }

            1 -> {
                TabListContent(movies = similar, openMovieDetail = openMovieDetail)
            }

            2 -> {
                MovieDetailVideoScreen(videos = videos)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TabListContent(movies: List<MovieWidgetModel>, openMovieDetail: (String) -> Unit) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        maxItemsInEachRow = 2,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        movies.forEach { movie ->
            Card(modifier = Modifier.fillMaxWidth(0.45f)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clickable { openMovieDetail(movie.movieId) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)

                ) {
                    MoviesImageView(imageUrl = movie.movieImage, modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}



