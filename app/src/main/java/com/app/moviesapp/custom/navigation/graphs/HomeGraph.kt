package com.app.moviesapp.custom.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.app.moviesapp.ui.HomeScreen
import com.app.moviesapp.ui.home.HomeListScreen
import com.app.moviesapp.ui.home.ListDetailScreen
import com.app.moviesapp.utils.ScreenRoutes

fun NavGraphBuilder.homeGraph(
    navController: NavController,
    shouldBottomBarVisible: (Boolean) -> Unit
) {
    navigation(
        startDestination = ScreenRoutes.HOME_ROUTE,
        route = ScreenRoutes.HOME_HOST_ROUTE
    ) {
        composable(
            route = ScreenRoutes.HOME_ROUTE
        ) {
            HomeScreen(
                onMovieClick = { route ->
                    navController.navigate(route)
                },
                openListScreen = { route ->
                    navController.navigate(route)
                }
            )
        }
        composable(
            route = ScreenRoutes.HOME_LIST_ROUTE,
            arguments = listOf(
                navArgument("type") {
                    type = NavType.StringType
                }
            )
        ) {
            HomeListScreen()
        }
        composable(
            route = ScreenRoutes.LIST_DETAIL_ROUTE,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) {
            ListDetailScreen()
        }
    }
}

enum class MovieListType(val type: String) {
    UPCOMING("upcoming"),
    POPULAR("popular"),
    TOP_RATED("top_rated"),
    NOW_PLAYING("now_playing")
}