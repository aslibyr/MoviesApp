package com.app.moviesapp.custom.bottom_bar

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.app.coins.custom.bottom_bar.BottomNavigationItem
import com.app.moviesapp.utils.ScreenRoutes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow


@Composable
fun BottomBar(
    navController: NavController,
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val navigation: (String) -> Unit = { route ->
        if (route == ScreenRoutes.BACK_PRESSED) {
            navController.popBackStack()
        } else {
            navController.navigate(route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }
        }
    }

    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            icon = Icons.Outlined.Home,
            route = ScreenRoutes.HOME_HOST_ROUTE
        ),
        BottomNavigationItem(
            title = "Search",
            icon = Icons.Filled.Search,
            route = ScreenRoutes.SEARCH_ROUTE
        ),
        BottomNavigationItem(
            title = "3",
            icon = Icons.Filled.Newspaper,
            route = ScreenRoutes.MOVIE_DETAIL_ROUTE
        )
    )


    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.onPrimary
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    navigation(item.route)
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 10.sp,
                    )
                },
                icon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = item.icon,
                        contentDescription = item.title,
                    )
                },
                interactionSource = NoRippleInteractionSource,
//                    colors = NavigationBarItemDefaults.colors(
//                        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
//                        selectedTextColor = MaterialTheme.colorScheme.onPrimary,
//                        unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
//                        unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
//                        indicatorColor = MaterialTheme.colorScheme.onSecondary
//                    )
            )
        }
    }

}

private object NoRippleInteractionSource : MutableInteractionSource {

    override val interactions: Flow<Interaction> = emptyFlow()

    override suspend fun emit(interaction: Interaction) {}

    override fun tryEmit(interaction: Interaction) = true
}