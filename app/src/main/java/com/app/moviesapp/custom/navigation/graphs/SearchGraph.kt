package com.app.moviesapp.custom.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.app.moviesapp.ui.search.SearchScreen
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
                onMovieClick = { route ->
                    navController.navigate(route)
                }
            )
        }
    }
}