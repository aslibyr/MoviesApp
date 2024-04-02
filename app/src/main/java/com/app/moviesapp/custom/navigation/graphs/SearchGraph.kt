package com.app.moviesapp.custom.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.app.moviesapp.ui.detail.DetailScreen
import com.app.moviesapp.ui.detail.screens.cast.MovieCastScreen
import com.app.moviesapp.ui.detail.screens.person.PersonScreen
import com.app.moviesapp.ui.movie_list.MovieListScreen
import com.app.moviesapp.ui.search.SearchScreen
import com.app.moviesapp.utils.Constant
import com.app.moviesapp.utils.ScreenRoutes

fun NavGraphBuilder.searchGraph(navController: NavController) {
    navigation(
        startDestination = ScreenRoutes.SEARCH_ROUTE,
        route = ScreenRoutes.SEARCH_HOST_ROUTE
    ) {
        composable(
            route = ScreenRoutes.SEARCH_ROUTE
        ) {
            SearchScreen(
                onMovieClick = { movieId ->
                    val route = ScreenRoutes.SEARCH_MOVIE_DETAIL_ROUTE.replace(
                        oldValue = Constant.ID,
                        newValue = movieId
                    )
                    navController.navigate(route)
                }
            )
        }
        composable(
            route = ScreenRoutes.SEARCH_LIST_ROUTE,
            arguments = listOf(
                navArgument("type") {
                    type = NavType.StringType
                },
                navArgument("movie_id") {
                    defaultValue = "movie"
                },
            )
        ) {
            MovieListScreen(onMovieClick = { movieId ->
                val route = ScreenRoutes.SEARCH_MOVIE_DETAIL_ROUTE.replace(
                    oldValue = Constant.ID,
                    newValue = movieId
                )
                navController.navigate(route)
            })
        }
        composable(
            route = ScreenRoutes.SEARCH_MOVIE_DETAIL_ROUTE,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) {
            DetailScreen(onBackClick = { navController.popBackStack() },
                openMovieListScreen = { type, movieId ->
                    val route = ScreenRoutes.SEARCH_LIST_ROUTE.replace(
                        oldValue = Constant.TYPE,
                        newValue = type
                    ).replace(
                        oldValue = "{movie_id}",
                        newValue = movieId
                    )
                    navController.navigate(route)
                },
                openMovieDetail = { movieId ->
                    val route = ScreenRoutes.SEARCH_MOVIE_DETAIL_ROUTE.replace(
                        oldValue = Constant.ID,
                        newValue = movieId
                    )
                    navController.navigate(route)
                },
                openCastScreen = { movieId ->
                    val route = ScreenRoutes.SEARCH_MOVIE_CAST_ROUTE.replace(
                        oldValue = "{movie_id}",
                        newValue = movieId
                    )
                    navController.navigate(route)
                },
                openPersonScreen = { personId ->
                    val route = ScreenRoutes.SEARCH_PERSON_ROUTE.replace(
                        oldValue = "{person_id}",
                        newValue = personId
                    )
                    navController.navigate(route)
                })
        }
        composable(
            route = ScreenRoutes.SEARCH_MOVIE_CAST_ROUTE,
            arguments = listOf(navArgument("movie_id") {
                type = NavType.StringType
            })
        ) {
            MovieCastScreen(openPersonScreen = { personId ->
                val route = ScreenRoutes.SEARCH_PERSON_ROUTE.replace(
                    oldValue = "{person_id}",
                    newValue = personId
                )
                navController.navigate(route)
            })
        }
        composable(
            route = ScreenRoutes.SEARCH_PERSON_ROUTE,
            arguments = listOf(navArgument("person_id") {
                type = NavType.StringType
            })
        ) {
            PersonScreen()
        }
    }
}