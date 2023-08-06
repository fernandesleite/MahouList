package me.fernandesleite.ui_components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.request.ImageRequest

private val brush = Brush.verticalGradient(
    colors = listOf(
        Color.Transparent,
        Color.Transparent,
        Color.Transparent,
        Color.Black
    )
)

@Composable
fun AnimeCoverWithNameScore(
    imageRequest: ImageRequest,
    placeholder: Painter,
    contentDescription: String,
    fontSize: Int,
    contentScale: ContentScale,
    modifier: Modifier = Modifier,
    animeName: String,
    animeScore: String
) {
    AnimeImageBox(
        imageRequest = imageRequest,
        placeholder = placeholder,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize(),
            onDraw = {
                drawRect(brush)
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 5.dp, horizontal = 8.dp),
            verticalArrangement = Arrangement.Bottom,
        ) {
            Box(modifier = Modifier
                .clip(RoundedCornerShape(100.dp))
                .background(Color(0xAA000000))
                .padding(horizontal = 5.dp)
            )
             {
                Text(
                    text = animeScore,
                    fontSize = fontSize.sp,
                    color = Color.White
                )
            }
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = fontSize.sp,
                text = animeName,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun AnimeCoverRoundedAsyncPreview() {
    val img = ImageRequest.Builder(LocalContext.current)
        .data(R.drawable.preview_cover)
        .crossfade(true)
        .build()

    AnimeCoverWithNameScore(
        imageRequest = img,
        placeholder = painterResource(R.drawable.preview_cover),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(140.dp)
            .height(180.dp),
        fontSize = 10,
        animeName = "Mushoku Tensei II: Isekai Ittara Honki Dasu - Shugo Jutsushi Fitz",
        animeScore = "5.78"
    )

}