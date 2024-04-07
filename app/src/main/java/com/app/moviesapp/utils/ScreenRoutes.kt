package com.app.moviesapp.utils

object ScreenRoutes {
    const val BACK_PRESSED = "back_pressed"

    //HOME ROUTES
    const val HOME_ROUTE = "home_route"
    const val HOME_HOST_ROUTE = "home_host_route"
    const val HOME_LIST_ROUTE = "home_list_route/{type}?movie_id={movie_id}"
    const val HOME_MOVIE_DETAIL_ROUTE = "home_movie_detail_route/{id}"
    const val HOME_MOVIE_CAST_ROUTE = "home_movie_cast_route?movie_id={movie_id}"
    const val HOME_PERSON_ROUTE = "home_person_route/{person_id}"

    //SEARCH ROUTES
    const val SEARCH_ROUTE = "search_route"
    const val SEARCH_HOST_ROUTE = "search_host_route"
    const val SEARCH_LIST_ROUTE = "search_list_route/{type}?movie_id={movie_id}"
    const val SEARCH_MOVIE_DETAIL_ROUTE = "search_movie_detail_route/{id}"
    const val SEARCH_MOVIE_CAST_ROUTE = "search_movie_cast_route?movie_id={movie_id}"
    const val SEARCH_PERSON_ROUTE = "search_person_route/{person_id}"

    //FAVORITE ROUTES
    const val FAVORITE_ROUTE = "favorite_route"
    const val FAVORITE_HOST_ROUTE = "favorite_host_route"

}