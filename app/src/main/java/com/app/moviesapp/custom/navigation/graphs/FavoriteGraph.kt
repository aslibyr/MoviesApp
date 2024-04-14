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
import com.app.moviesapp.ui.favorite.FavoriteScreen
import com.app.moviesapp.utils.Constant
import com.app.moviesapp.utils.ScreenRoutes

fun NavGraphBuilder.favoriteGraph(
    navController: NavController
) {
    navigation(
        startDestination = ScreenRoutes.FAVORITE_ROUTE,
        route = ScreenRoutes.FAVORITE_HOST_ROUTE
    ) {
        composable(route = ScreenRoutes.FAVORITE_ROUTE) {
            FavoriteScreen(
                onMovieClick = { movieId ->
                    val route = ScreenRoutes.FAVORITE_MOVIE_DETAIL_ROUTE.replace(
                        oldValue = Constant.ID,
                        newValue = movieId
                    )
                    navController.navigate(route)
                },
                onPersonClick = { personId ->
                    val route = ScreenRoutes.FAVORITE_PERSON_ROUTE.replace(
                        oldValue = "{person_id}",
                        newValue = personId
                    )
                    navController.navigate(route)
                }
            )
        }
        composable(
            route = ScreenRoutes.FAVORITE_MOVIE_DETAIL_ROUTE,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) {
            DetailScreen(onBackClick = { navController.popBackStack() },
                openMovieListScreen = { type, movieId ->
                    val route = ScreenRoutes.FAVORITE_LIST_ROUTE.replace(
                        oldValue = Constant.TYPE,
                        newValue = type
                    ).replace(
                        oldValue = "{movie_id}",
                        newValue = movieId
                    )
                    navController.navigate(route)
                },
                openMovieDetail = { movieId ->
                    val route = ScreenRoutes.FAVORITE_MOVIE_DETAIL_ROUTE.replace(
                        oldValue = Constant.ID,
                        newValue = movieId
                    )
                    navController.navigate(route)
                },
                openCastScreen = { movieId ->
                    val route = ScreenRoutes.FAVORITE_MOVIE_CAST_ROUTE.replace(
                        oldValue = "{movie_id}",
                        newValue = movieId
                    )
                    navController.navigate(route)
                },
                openPersonScreen = { personId ->
                    val route = ScreenRoutes.FAVORITE_PERSON_ROUTE.replace(
                        oldValue = "{person_id}",
                        newValue = personId
                    )
                    navController.navigate(route)
                })
        }
        composable(
            route = ScreenRoutes.FAVORITE_MOVIE_CAST_ROUTE,
            arguments = listOf(navArgument("movie_id") {
                type = NavType.StringType
            })
        ) {
            MovieCastScreen(openPersonScreen = { personId ->
                val route = ScreenRoutes.FAVORITE_PERSON_ROUTE.replace(
                    oldValue = "{person_id}",
                    newValue = personId
                )
                navController.navigate(route)
            })
        }
        composable(
            route = ScreenRoutes.FAVORITE_PERSON_ROUTE,
            arguments = listOf(navArgument("person_id") {
                type = NavType.StringType
            })
        ) {
            PersonScreen()
        }

    }

}