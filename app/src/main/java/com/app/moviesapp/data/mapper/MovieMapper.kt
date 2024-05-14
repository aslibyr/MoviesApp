package com.app.moviesapp.data.mapper

import com.app.moviesapp.custom.widget.MovieWidgetModel
import com.app.moviesapp.data.local.entity.FavoriteMovieEntity
import com.app.moviesapp.data.response.MovieDetailResponse
import com.app.moviesapp.data.response.MovieResponse
import com.app.moviesapp.data.ui_models.MovieDetailUIModel
import com.app.moviesapp.utils.Constant


fun MovieResponse.MovieWidgetModel(): MovieWidgetModel {
    return MovieWidgetModel(
        movieId = this.id.toString(),
        movieName = this.title,
        movieImage = this.getImagePath(),
        voteAvg = this.vote_average ?: "",
        releaseDate = this.release_date ?: ""
    )
}

fun MovieDetailResponse.toUIModel(isFavorite: Boolean): MovieDetailUIModel {
    return MovieDetailUIModel(
        title = this.title ?: "",
        overview = this.overview ?: "",
        movieId = this.id.toString(),
        duration = "${this.runtime} min.",
        movieImage = Constant.BASE_POSTER_URL + this.poster_path,
        isFavorite = isFavorite,
        voteAvg = this.vote_average ?: "",
        releaseDate = this.release_date ?: ""
    )
}

fun MovieDetailUIModel.toFavoriteMovieEntity(): FavoriteMovieEntity {
    return FavoriteMovieEntity(
        title = this.title,
        overview = this.overview,
        movieId = this.movieId,
        duration = this.duration,
        movieImage = this.movieImage,
        voteAvg = this.voteAvg,
        releaseDate = this.releaseDate
    )
}

fun FavoriteMovieEntity.toUIModel(): MovieDetailUIModel {
    return MovieDetailUIModel(
        title = this.title,
        overview = this.overview,
        movieId = this.movieId,
        duration = this.duration,
        isFavorite = true,
        movieImage = this.movieImage,
        voteAvg = this.voteAvg,
        releaseDate = this.releaseDate
    )
}