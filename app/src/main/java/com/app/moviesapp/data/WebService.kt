package com.app.moviesapp.data

import com.app.moviesapp.base.BasePagingResponse
import com.app.moviesapp.data.response.MovieCreditResponse
import com.app.moviesapp.data.response.MovieDetailResponse
import com.app.moviesapp.data.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {
    @GET("movie/now_playing")
    suspend fun getNowPLaying(
        @Query("page") page: Int
    ): BasePagingResponse<MovieResponse>

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("page") page: Int
    ): BasePagingResponse<MovieResponse>

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("page") page: Int
    ): BasePagingResponse<MovieResponse>

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("page") page: Int
    ): BasePagingResponse<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id")  id: String
    ): MovieDetailResponse
    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id")  id: String
    ): MovieCreditResponse


}