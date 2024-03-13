package com.app.moviesapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.moviesapp.custom.navigation.graphs.MovieListType
import com.app.moviesapp.data.WebService
import com.app.moviesapp.data.response.MovieResponse
import okio.IOException
import retrofit2.HttpException

class MoviePagingSource(
    private val api: WebService,
    private val movieType: MovieListType,
    private val query: String = "",
) : PagingSource<Int, MovieResponse>() {
    override fun getRefreshKey(state: PagingState<Int, MovieResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse> {
        val page = params.key ?: 1

        return try {
            val response = when (movieType) {
                MovieListType.POPULAR -> {
                    api.getPopular(page)
                }

                MovieListType.TOP_RATED -> {
                    api.getTopRated(page)
                }

                MovieListType.UPCOMING -> {
                    api.getUpcoming(page)
                }

                MovieListType.NOW_PLAYING -> {
                    api.getNowPLaying(page)
                }

            }

            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.results.isEmpty()) null else page.plus(1)
            )

        } catch (e: IOException) {
            LoadResult.Error(throwable = e)
        } catch (e: HttpException) {
            LoadResult.Error(throwable = e)
        }
    }
}