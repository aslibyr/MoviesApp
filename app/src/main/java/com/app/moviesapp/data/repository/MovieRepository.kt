package com.app.moviesapp.data.repository

import com.app.moviesapp.data.WebService
import com.app.moviesapp.data.local.MoviesAppDataBase
import com.app.moviesapp.data.mapper.toFavoriteMovieEntity
import com.app.moviesapp.data.mapper.toUIModel
import com.app.moviesapp.data.ui_models.MovieDetailUIModel
import com.app.moviesapp.data.ui_models.MovieVideoUIModel
import com.app.moviesapp.utils.ResultWrapper
import com.app.moviesapp.utils.ResultWrapperLocal
import com.app.moviesapp.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val appDataBase: MoviesAppDataBase,
    private val webService: WebService
) {

    suspend fun getMovieDetails(id: String): ResultWrapper<MovieDetailUIModel> {
        return when (val response = safeApiCall(Dispatchers.IO) {
            webService.getMovieDetails(id)
        }) {
            is ResultWrapper.Success -> {
                val responseData = response.value
                val favoriteMovie =
                    appDataBase.favoriteMovies().getFavoriteMovie(responseData.id.toString())
                val isFavorite = favoriteMovie != null
                val movieDetailUIModel = responseData.toUIModel(isFavorite = isFavorite)
                ResultWrapper.Success(movieDetailUIModel)
            }

            is ResultWrapper.GenericError -> {
                ResultWrapper.GenericError()
            }

            ResultWrapper.Loading -> {
                ResultWrapper.Loading
            }

            ResultWrapper.NetworkError -> {
                ResultWrapper.NetworkError
            }
        }
    }

    suspend fun addMovieToFavorite(movie: MovieDetailUIModel): ResultWrapperLocal<MovieDetailUIModel> {
        return try {
            appDataBase.favoriteMovies().insertFavoriteMovie(movie.toFavoriteMovieEntity())
            val newMovieDetailData = MovieDetailUIModel(
                title = movie.title,
                overview = movie.overview,
                movieId = movie.movieId,
                duration = movie.duration,
                isFavorite = true
            )
            ResultWrapperLocal.Success(newMovieDetailData)
        } catch (e: Exception) {
            ResultWrapperLocal.Error(e.message ?: "")
        }
    }

    fun removeMovieFromFavorite(movie: MovieDetailUIModel): ResultWrapperLocal<MovieDetailUIModel> {
        return try {
            appDataBase.favoriteMovies().removeFavoriteMovie(movie.movieId)
            val newMovieDetailData = MovieDetailUIModel(
                title = movie.title,
                overview = movie.overview,
                movieId = movie.movieId,
                duration = movie.duration,
                isFavorite = false
            )
            ResultWrapperLocal.Success(newMovieDetailData)
        } catch (e: Exception) {
            ResultWrapperLocal.Error(e.message ?: "")
        }
    }

    fun getFavoriteMovies(): Flow<ResultWrapperLocal<List<MovieDetailUIModel>>> {
        return flow {
            appDataBase.favoriteMovies().getFavoriteMovies().map { list ->
                list.map {
                    it.toUIModel()
                }
            }.collect {
                emit(ResultWrapperLocal.Success(it))
            }
        }
    }

    suspend fun getVideos(movieId: String): ResultWrapper<List<MovieVideoUIModel>> {
        return when (val response = safeApiCall(Dispatchers.IO) {
            webService.getVideos(movieId)
        }) {
            is ResultWrapper.GenericError -> {
                ResultWrapper.GenericError()
            }

            ResultWrapper.Loading -> {
                ResultWrapper.Loading
            }

            ResultWrapper.NetworkError -> {
                ResultWrapper.NetworkError
            }

            is ResultWrapper.Success -> {
                val data = response.value.results.filter {
                    it.iso_639_1 == "en"
                }
                ResultWrapper.Success(data.map { it.toUIModel() })
            }
        }
    }
}
