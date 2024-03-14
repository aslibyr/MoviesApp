package com.app.moviesapp.custom.navigation


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.app.moviesapp.custom.navigation.graphs.homeGraph
import com.app.moviesapp.custom.navigation.graphs.searchGraph
import com.app.moviesapp.utils.ScreenRoutes

@RequiresApi(Build.VERSION_CODES.S)
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
        searchGraph(navController)
    }
}