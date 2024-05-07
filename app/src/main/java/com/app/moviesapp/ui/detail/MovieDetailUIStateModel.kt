package com.app.moviesapp.ui.detail

import com.app.moviesapp.custom.widget.CastWidgetModel
import com.app.moviesapp.custom.widget.MovieWidgetModel
import com.app.moviesapp.data.ui_models.MovieDetailUIModel
import com.app.moviesapp.data.ui_models.MovieReviewsUIModel
import com.app.moviesapp.data.ui_models.MovieVideoUIModel

sealed class DetailScreenUIState {
    data class UpdateTabIndex(val tabIndex: Int) : DetailScreenUIState()
}
data class MovieDetailUIStateModel(
    val images: List<String> = emptyList(),
    val movieDetailData: MovieDetailUIModel = MovieDetailUIModel(),
    val movieCastData: List<CastWidgetModel> = emptyList(),
    val movieRecommendations: List<MovieWidgetModel> = emptyList(),
    val movieSimilar: List<MovieWidgetModel> = emptyList(),
    val movieReviews: List<MovieReviewsUIModel> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String = "",
    val successCount: Int = 0,
    val tabIndex: Int = 0,
    val videoData: List<MovieVideoUIModel> = emptyList(),
)