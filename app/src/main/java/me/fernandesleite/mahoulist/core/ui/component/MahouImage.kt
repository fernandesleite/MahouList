package me.fernandesleite.mahoulist.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.fernandesleite.mahoulist.R

@Composable
fun MahouImage(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    width: Dp = 150.dp,
    height: Dp = 200.dp,
    imageUrl: String,
    contentDescription: String
) {
    AsyncImage(
        placeholder = painterResource(id = R.drawable.loading_cover),
        error = painterResource(id = R.drawable.error_cover),
        modifier = modifier
            .width(width)
            .height(height),
        contentScale = contentScale,
        model = imageUrl, contentDescription = contentDescription
    )
}

@Composable
fun MahouImage(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    width: Dp = 150.dp,
    height: Dp = 200.dp,
    resourceImage: Int,
    contentDescription: String
) {
    Image(
        modifier = modifier
            .width(width)
            .height(height),
        contentScale = contentScale,
        painter = painterResource(id = resourceImage),
        contentDescription = contentDescription
    )
}