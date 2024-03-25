package com.app.moviesapp.ui.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun MovieDetailsComponent(title: String, overview: String) {
    Column {

        Text(
            fontSize = (24.sp),
            text = title,
            fontWeight = FontWeight.Bold
        )
        Text(text = overview, fontSize = 14.sp)
    }
}