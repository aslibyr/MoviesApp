package com.app.moviesapp.custom.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.app.moviesapp.ui.favorite.FavoriteScreen
import com.app.moviesapp.utils.ScreenRoutes

fun NavGraphBuilder.favoriteGraph(
    navController: NavController
) {
    navigation(
        startDestination = ScreenRoutes.FAVORITE_ROUTE,
        route = ScreenRoutes.FAVORITE_HOST_ROUTE
    ) {
        composable(route = ScreenRoutes.FAVORITE_ROUTE) {
            FavoriteScreen()
        }
    }

}