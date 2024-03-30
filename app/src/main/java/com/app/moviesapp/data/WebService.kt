package com.app.moviesapp.data

import com.app.moviesapp.base.BasePagingResponse
import com.app.moviesapp.data.response.MovieCreditResponse
import com.app.moviesapp.data.response.MovieDetailResponse
import com.app.moviesapp.data.response.MovieImageResponse
import com.app.moviesapp.data.response.MovieResponse
import com.app.moviesapp.data.response.MovieReviewsResponse
import com.app.moviesapp.data.response.PersonResponse
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

    @GET("search/movie")
    suspend fun getSearchMovieApi(
        @Query("page") page: Int,
        @Query("query") query: String
    ): BasePagingResponse<MovieResponse>

    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecommendations(
        @Path("movie_id") id: String,
        @Query("page") page: Int
    ): BasePagingResponse<MovieResponse>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilar(
        @Path("movie_id") id: String,
        @Query("page") page: Int
    ): BasePagingResponse<MovieResponse>

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviews(
        @Path("movie_id") id: String,
        @Query("page") page: Int
    ): MovieReviewsResponse

    @GET("movie/{movie_id}/images")
    suspend fun getImages(
        @Path("movie_id") id: String,
    ): MovieImageResponse

    @GET("person/{person_id}")
    suspend fun getPerson(
        @Path("person_id") id: String,
    ): PersonResponse
}