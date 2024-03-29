package com.app.moviesapp.ui.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MovieDetailsComponent(title: String, overview: String, duration: String) {
    Column(modifier = Modifier.padding(4.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            fontSize = (24.sp),
            text = title,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = duration,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
        )
        Text(text = overview, fontSize = 14.sp)
    }
}