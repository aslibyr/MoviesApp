package com.app.moviesapp.custom.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
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
            route= ScreenRoutes.HOME_ROUTE
        ){
            HomeScreen()
        }
        composable(
            route= ScreenRoutes.HOME_LIST_ROUTE
        ){
            HomeListScreen()
        }
        composable(
            route= ScreenRoutes.LIST_DETAIL_ROUTE
        ){
            ListDetailScreen()
        }
    }
}