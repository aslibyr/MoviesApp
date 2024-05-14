package com.app.moviesapp.custom.tab

import com.app.moviesapp.ui.favorite.TabItemModel

fun getTabList(): List<TabItemModel> {
    return arrayListOf(
        TabItemModel(
            "Recommendations",
            "recommendations"
        ),
        TabItemModel(
            "Similar",
            "similar"
        ),
        TabItemModel(
            "Videos",
            "videos"
        ),
    )
}

data class TabItemModel(
    val title: String,
    val id: String
)