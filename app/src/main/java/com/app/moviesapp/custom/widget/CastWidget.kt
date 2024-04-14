package com.app.moviesapp.custom.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.moviesapp.custom.image.MoviesImageView

@Composable
fun CastWidget(
    model: CastWidgetComponentModel,
    openCastListScreen: () -> Unit,
    openPersonScreen: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clickable {
                    openCastListScreen()
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Cast", style = MaterialTheme.typography.bodyMedium
            )
            Icon(
                imageVector = Icons.Filled.ArrowForwardIos,
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
        }
        LazyRow(Modifier.fillMaxWidth(), contentPadding = PaddingValues(start = 16.dp)) {
            items(model.cast) { cast ->
                CastWidgetItem(cast = cast, openPersonScreen = openPersonScreen)
            }
        }
    }
}


@Composable
fun CastWidgetItem(
    cast: CastWidgetModel, openPersonScreen: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    openPersonScreen(cast.personId.toString())
                },
            elevation = CardDefaults.cardElevation(5.dp),
            shape = RoundedCornerShape(15.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            MoviesImageView(imageUrl = cast.profilePath, modifier = Modifier.size(150.dp))

            Text(
                modifier = Modifier.padding(start = 8.dp, bottom = 4.dp, top = 4.dp),
                text = cast.castName,
                fontSize = 12.sp,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                modifier = Modifier.padding(start = 8.dp, bottom = 4.dp),
                text = cast.character,
                fontSize = 10.sp,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}