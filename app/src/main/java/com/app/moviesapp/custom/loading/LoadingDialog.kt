package com.app.moviesapp.custom.loading

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun LoadingDialog(
    dismissOnBackPress: Boolean = false,
    dismissOnClickOutside: Boolean = false
) {
    Dialog(
        onDismissRequest = { },
        DialogProperties(
            dismissOnBackPress = dismissOnBackPress,
            dismissOnClickOutside = dismissOnClickOutside
        )
    ) {
        CircularProgressIndicator(color = Color.White)
    }
}