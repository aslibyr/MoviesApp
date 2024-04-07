package com.app.moviesapp.ui.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.app.moviesapp.ui.favorite.screens.movies.FavoriteMoviesScreen
import com.app.moviesapp.ui.favorite.screens.people.FavoritePeopleScreen

@Composable
fun FavoriteScreen() {
    var selectedTabIndex by rememberSaveable {
        mutableStateOf(0)
    }

    val items = mutableListOf(
        TabItemModel(
            title = "Movie",
            id = ""
        ),
        TabItemModel(
            title = "People",
            id = ""
        )
    )
    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = MaterialTheme.colorScheme.onPrimary,
            divider = {},
            indicator = {
                Divider(
                    modifier = Modifier
                        .tabIndicatorOffset(it[selectedTabIndex])
                )
            }
        ) {
            items.forEachIndexed { index, tabItemModel ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = {
                        Text(
                            text = tabItemModel.title,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.secondary,
                                fontSize = 12.sp
                            ),
                        )

                    },
                    icon = {
                        val selectedIcon =
                            if (tabItemModel.title == "Movie") Icons.Filled.Movie else Icons.Filled.PersonOutline

                        Icon(
                            imageVector = selectedIcon,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    },
                )
            }
        }
        when (selectedTabIndex) {
            0 -> {
                FavoriteMoviesScreen()
            }

            1 -> {
                FavoritePeopleScreen()
            }
        }
    }

}

data class TabItemModel(
    val title: String,
    val id: String = ""
)