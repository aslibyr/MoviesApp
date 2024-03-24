package com.app.moviesapp.custom.widget

data class CastWidgetComponentModel(val cast: List<CastWidgetModel>)

data class CastWidgetModel(
    val castName: String = "",
    val character: String = "",
    val profilePath: String = ""
)
