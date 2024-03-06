package com.app.moviesapp.custom.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.app.moviesapp.custom.navigation.graphs.homeGraph
import com.app.moviesapp.utils.ScreenRoutes

@Composable
fun MainNavigation(
    navController: NavHostController,
    modifier: Modifier
) {

    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.HOME_HOST_ROUTE,
        modifier = Modifier,

        ) {
        homeGraph(navController, shouldBottomBarVisible = {})

    }
}