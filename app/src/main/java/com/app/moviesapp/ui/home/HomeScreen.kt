package com.app.moviesapp.ui.home

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.app.moviesapp.R
import com.app.moviesapp.base.BasePagingResponse
import com.app.moviesapp.custom.indicator.PagerIndicator
import com.app.moviesapp.custom.mapper.MovieWidgetModel
import com.app.moviesapp.custom.navigation.graphs.MovieListType
import com.app.moviesapp.custom.widget.MovieWidget
import com.app.moviesapp.custom.widget.MovieWidgetComponentModel
import com.app.moviesapp.data.response.MovieResponse
import com.app.moviesapp.utils.Constant
import com.app.moviesapp.utils.ResultWrapper
import com.app.moviesapp.utils.ScreenRoutes

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    onMovieClick: (String) -> Unit,
    openListScreen: (String) -> Unit

) {
    val context = LocalContext.current
    val nowPlayingState by viewModel.nowPlaying.collectAsStateWithLifecycle()
    val popularState by viewModel.popular.collectAsStateWithLifecycle()
    val topRatedState by viewModel.topRated.collectAsStateWithLifecycle()
    val upcomingState by viewModel.upcoming.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        when (nowPlayingState) {
            is ResultWrapper.GenericError -> {
                val response = (nowPlayingState as ResultWrapper.GenericError).error
                response?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }

            ResultWrapper.Loading -> {}
            ResultWrapper.NetworkError -> {
                Toast.makeText(context, "Network error", Toast.LENGTH_LONG).show()
            }

            is ResultWrapper.Success -> {
                val response =
                    (nowPlayingState as ResultWrapper.Success<BasePagingResponse<MovieResponse>>).value

                val pagerState = rememberPagerState(
                    initialPage = 0,
                    initialPageOffsetFraction = 0f,
                    pageCount = { response.results.take(10).size }
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp)
                ) {
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        val url = response.results[it].getImagePath()
                        AsyncImage(
                            model = url,
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    onMovieClick(
                                        ScreenRoutes.MOVIE_DETAIL_ROUTE.replace(
                                            oldValue = Constant.ID,
                                            newValue = response.results[it].id.toString()
                                        )
                                    )
                                },
                            contentScale = ContentScale.Crop
                        )
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
                }



                MovieWidget(
                    model = MovieWidgetComponentModel(
                        widgetCategory = "Now Playing",
                        movies = response.results.map { it.MovieWidgetModel() }),
                    openListScreen = {
                        openListScreen(
                            ScreenRoutes.HOME_LIST_ROUTE.replace(
                                oldValue = Constant.TYPE,
                                newValue = MovieListType.NOW_PLAYING.type
                            )
                        )
                    },
                    onMovieClick,
                )
            }
        }
        when (popularState) {
            is ResultWrapper.GenericError -> {
                val response = (popularState as ResultWrapper.GenericError).error
                response?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }

            ResultWrapper.Loading -> {}
            ResultWrapper.NetworkError -> {}
            is ResultWrapper.Success -> {
                val response =
                    (popularState as ResultWrapper.Success<BasePagingResponse<MovieResponse>>).value
                MovieWidget(model = MovieWidgetComponentModel(
                    widgetCategory = stringResource(R.string.popular),
                    movies = response.results.map { it.MovieWidgetModel() }
                ), openListScreen = {
                    openListScreen(
                        ScreenRoutes.HOME_LIST_ROUTE.replace(
                            oldValue = Constant.TYPE,
                            newValue = MovieListType.POPULAR.type
                        )
                    )
                }, onMovieClick
                )
            }
        }
        when (topRatedState) {
            is ResultWrapper.GenericError -> {
                val response = (topRatedState as ResultWrapper.GenericError).error
                response?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }

            ResultWrapper.Loading -> {}
            ResultWrapper.NetworkError -> {}
            is ResultWrapper.Success -> {
                val response =
                    (topRatedState as ResultWrapper.Success<BasePagingResponse<MovieResponse>>).value
                MovieWidget(
                    model = MovieWidgetComponentModel(
                        widgetCategory = "Top Rated",
                        movies = response.results.map { it.MovieWidgetModel() }),
                    openListScreen = {
                        openListScreen(
                            ScreenRoutes.HOME_LIST_ROUTE.replace(
                                oldValue = Constant.TYPE,
                                newValue = MovieListType.TOP_RATED.type
                            )
                        )
                    },
                    onMovieClick,
                )
            }
        }
        when (upcomingState) {
            is ResultWrapper.GenericError -> {
                val response = (upcomingState as ResultWrapper.GenericError).error
                response?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }

            ResultWrapper.Loading -> {}
            ResultWrapper.NetworkError -> {}
            is ResultWrapper.Success -> {
                val response =
                    (upcomingState as ResultWrapper.Success<BasePagingResponse<MovieResponse>>).value
                MovieWidget(
                    model = MovieWidgetComponentModel(
                        widgetCategory = "Upcoming",
                        movies = response.results.map { it.MovieWidgetModel() }),
                    openListScreen = {
                        openListScreen(
                            ScreenRoutes.HOME_LIST_ROUTE.replace(
                                oldValue = Constant.TYPE,
                                newValue = MovieListType.UPCOMING.type
                            )
                        )
                    },
                    onMovieClick,
                )
            }
        }
    }
}