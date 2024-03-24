package com.app.moviesapp.custom.widget

data class CastWidgetComponentModel(
    val widgetTitle : String = "",
    val cast: List<CastWidgetModel>
)

data class CastWidgetModel(
    val castName: String = "",
    val character: String = "",
    val profilePath: String = "",
    val personId : Int = 0
)
