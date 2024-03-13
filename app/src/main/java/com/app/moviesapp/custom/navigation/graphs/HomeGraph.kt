package com.app.moviesapp.custom.navigation.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.app.moviesapp.ui.home.HomeScreen
import com.app.moviesapp.ui.detail.ListDetailScreen
import com.app.moviesapp.ui.movie_list.MovieListScreen
import com.app.moviesapp.utils.ScreenRoutes

@RequiresApi(Build.VERSION_CODES.S)
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
            MovieListScreen()
        }
        composable(
            route = ScreenRoutes.MOVIE_DETAIL_ROUTE,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) {
            ListDetailScreen(onBackClick = { navController.popBackStack() })
        }
    }
}

enum class MovieListType(val type: String) {
    UPCOMING("upcoming"),
    POPULAR("popular"),
    TOP_RATED("top_rated"),
    NOW_PLAYING("now_playing")
}