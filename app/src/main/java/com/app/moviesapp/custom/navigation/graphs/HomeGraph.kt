package com.app.moviesapp.custom.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.app.moviesapp.ui.detail.DetailScreen
import com.app.moviesapp.ui.detail.screens.cast.MovieCastScreen
import com.app.moviesapp.ui.detail.screens.person.PersonScreen
import com.app.moviesapp.ui.detail.screens.videos.MovieDetailVideoScreen
import com.app.moviesapp.ui.home.HomeScreen
import com.app.moviesapp.ui.movie_list.MovieListScreen
import com.app.moviesapp.utils.Constant
import com.app.moviesapp.utils.ScreenRoutes

fun NavGraphBuilder.homeGraph(
    navController: NavController,
    shouldBottomBarVisible: (Boolean) -> Unit = {}
) {
    navigation(
        startDestination = ScreenRoutes.HOME_ROUTE,
        route = ScreenRoutes.HOME_HOST_ROUTE
    ) {
        composable(
            route = ScreenRoutes.HOME_ROUTE
        ) {
            HomeScreen(
                onMovieClick = { movieId ->
                    val route = ScreenRoutes.HOME_MOVIE_DETAIL_ROUTE.replace(
                        oldValue = Constant.ID,
                        newValue = movieId
                    )
                    navController.navigate(route)
                },
                openListScreen = { type ->
                    val route = ScreenRoutes.HOME_LIST_ROUTE.replace(
                        oldValue = Constant.TYPE,
                        newValue = type
                    )
                    navController.navigate(route)
                }
            )
        }
        composable(
            route = ScreenRoutes.HOME_LIST_ROUTE,
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
                val route = ScreenRoutes.HOME_MOVIE_DETAIL_ROUTE.replace(
                    oldValue = Constant.ID,
                    newValue = movieId
                )
                navController.navigate(route)
            })
        }
        composable(
            route = ScreenRoutes.HOME_MOVIE_DETAIL_ROUTE,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) {
            DetailScreen(onBackClick = { navController.popBackStack() },
                openMovieListScreen = { type, movieId ->
                    val route = ScreenRoutes.HOME_LIST_ROUTE.replace(
                        oldValue = Constant.TYPE,
                        newValue = type
                    ).replace(
                        oldValue = "{movie_id}",
                        newValue = movieId
                    )
                    navController.navigate(route)
                },
                openMovieDetail = { movieId ->
                    val route = ScreenRoutes.HOME_MOVIE_DETAIL_ROUTE.replace(
                        oldValue = Constant.ID,
                        newValue = movieId
                    )
                    navController.navigate(route)
                },
                openCastScreen = { movieId ->
                    val route = ScreenRoutes.HOME_MOVIE_CAST_ROUTE.replace(
                        oldValue = "{movie_id}",
                        newValue = movieId
                    )
                    navController.navigate(route)
                },
                openPersonScreen = { personId ->
                    val route = ScreenRoutes.HOME_PERSON_ROUTE.replace(
                        oldValue = "{person_id}",
                        newValue = personId
                    )
                    navController.navigate(route)
                })
        }
        composable(
            route = ScreenRoutes.HOME_MOVIE_CAST_ROUTE,
            arguments = listOf(navArgument("movie_id") {
                type = NavType.StringType
            })
        ) {
            MovieCastScreen(openPersonScreen = { personId ->
                val route = ScreenRoutes.HOME_PERSON_ROUTE.replace(
                    oldValue = "{person_id}",
                    newValue = personId
                )
                navController.navigate(route)
            })
        }
        composable(
            route = ScreenRoutes.HOME_PERSON_ROUTE,
            arguments = listOf(navArgument("person_id") {
                type = NavType.StringType
            })
        ) {
            PersonScreen()
        }
        composable(
            route = ScreenRoutes.HOME_VIDEO_ROUTE,
            arguments = listOf(navArgument("movie_id") {
                type = NavType.StringType
            })
        ) {
            MovieDetailVideoScreen()
        }
    }
}

enum class MovieListType(val type: String) {
    UPCOMING("upcoming"),
    POPULAR("popular"),
    TOP_RATED("top_rated"),
    NOW_PLAYING("now_playing"),
    SIMILAR("similar"),
    RECOMMENDATIONS("recommendations")
}