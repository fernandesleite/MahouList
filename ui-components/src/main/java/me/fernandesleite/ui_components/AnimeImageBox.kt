package me.fernandesleite.ui_components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
fun AnimeImageBox(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    imageRequest: ImageRequest,
    placeholder: Painter,
    contentDescription: String,
    content: @Composable() (() -> Unit)? = null
) {

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
    ) {
        AsyncImage(
            model = imageRequest,
            contentDescription = contentDescription,
            contentScale = contentScale,
            placeholder = placeholder,
            modifier = modifier
        )
        if (content != null) {
            content()
        }
    }
}


@Preview
@Composable
private fun AnimeCoverPreview() {
    val img = ImageRequest.Builder(LocalContext.current)
        .data(R.drawable.preview_cover)
        .crossfade(true)
        .build()

    AnimeImageBox(
        modifier = Modifier
            .width(140.dp)
            .height(180.dp),
        imageRequest = img,
        placeholder = painterResource(R.drawable.preview_cover),
        contentDescription = "",
        contentScale = ContentScale.Crop
    )
}
