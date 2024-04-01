package com.app.moviesapp.custom.popup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import coil.compose.SubcomposeAsyncImage
import com.app.coins.utils.heightPercent
import com.app.coins.utils.noRippleClick

@Composable
fun CustomImagePopUp(
    modifier: Modifier = Modifier,
    image: String,
    onDismissRequest: () -> Unit,
) {

    val configuration = LocalConfiguration.current
    Dialog(onDismissRequest = { onDismissRequest() }) {

        (LocalView.current.parent as DialogWindowProvider).window.setDimAmount(0.15f)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            Image(imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 48.dp)
                    .size(24.dp)
                    .background(Color.White, shape = RoundedCornerShape(50.dp))
                    .noRippleClick {
                        onDismissRequest()
                    }
            )
            Card(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .heightPercent(0.50f, configuration = configuration),
                shape = RoundedCornerShape(5.dp)
            ) {
                SubcomposeAsyncImage(
                    model = image,
                    modifier = modifier.fillMaxSize(),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }

        }

    }
}