package com.app.moviesapp.data.mapper

import com.app.moviesapp.custom.widget.MovieWidgetModel
import com.app.moviesapp.data.local.entity.FavoriteMovieEntity
import com.app.moviesapp.data.response.MovieDetailResponse
import com.app.moviesapp.data.response.MovieResponse
import com.app.moviesapp.data.ui_models.MovieDetailUIModel
import com.app.moviesapp.utils.Constant


fun MovieResponse.MovieWidgetModel() : MovieWidgetModel {
    return MovieWidgetModel(
        movieId = this.id.toString(),
        movieName = this.title,
        movieImage = this.getImagePath()
    )
}

fun MovieDetailResponse.toUIModel(isFavorite : Boolean): MovieDetailUIModel {
    return MovieDetailUIModel(
        title = this.title ?: "",
        overview = this.overview ?: "",
        movieId = this.id.toString(),
        duration = "${this.runtime} min.",
        movieImage = Constant.BASE_POSTER_URL + this.poster_path,
        isFavorite = isFavorite,
    )
}

fun MovieDetailUIModel.toFavoriteMovieEntity(): FavoriteMovieEntity {
    return FavoriteMovieEntity(
        title = this.title ,
        overview = this.overview ,
        movieId = this.movieId,
        duration = "${this.duration} min.",
        movieImage = this.movieImage
    )
}

fun FavoriteMovieEntity.toUIModel(): MovieDetailUIModel {
    return MovieDetailUIModel(
        title = this.title,
        overview = this.overview,
        movieId = this.movieId,
        duration = "${this.duration} min.",
        isFavorite = true,
        movieImage = this.movieImage,
    )
}