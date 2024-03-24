package com.app.moviesapp.custom.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.app.moviesapp.data.response.MovieCreditResponse

@Composable
fun CastWidget(
    model: MovieCreditResponse,
    openCastListScreen: () -> Unit,
    onMovieClick: (String) -> Unit
) {

    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Tümünü gör", style = MaterialTheme.typography.bodyMedium
            )
            Icon(
                imageVector = Icons.Filled.ArrowForwardIos,
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
        }
        LazyRow(Modifier.fillMaxWidth(), contentPadding = PaddingValues(start = 16.dp)) {
            items(model.cast) { cast ->
                CastWidget(model =) {

                }
            }
        }
    }
}


@Composable
fun CastWidgetItem(
    cast: CastWidgetModel, onCastClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(5.dp),
            shape = RoundedCornerShape(15.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            AsyncImage(model = cast.profilePath, contentDescription = "")
            Text(
                modifier = Modifier.padding(8.dp),
                text = cast.castName,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = cast.character,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}