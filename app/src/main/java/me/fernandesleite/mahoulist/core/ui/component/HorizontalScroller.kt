package me.fernandesleite.mahoulist.core.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.fernandesleite.mahoulist.R
import me.fernandesleite.mahoulist.core.data.model.remote.common.Anime
import me.fernandesleite.mahoulist.core.data.model.remote.common.MainPicture


@Composable
fun HorizontalScroller(
    modifier: Modifier = Modifier,
    addMore: Boolean = true,
    isLoading: Boolean = false,
    title: String,
    animeList: List<Anime> = listOf(),
    onClickItem: (Anime) -> Unit = {},
    onClickMore: () -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.titleLarge,
        )
        if (!isLoading) {
            LazyRow(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(end = 8.dp)
            ) {

                items(items = animeList, key = { item -> item.id }, itemContent = { item ->
                    Surface(
                        modifier = Modifier.clickable { onClickItem(item) },
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                        shadowElevation = 8.dp
                    ) {
                        MahouImage(
                            imageUrl = item.mainPicture.medium,
                            contentDescription = item.title
                        )
                    }


                })

                if (addMore) {
                    item {
                        Surface(
                            modifier = Modifier
                                .width(150.dp)
                                .height(200.dp)
                                .clickable { onClickMore() },
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                            shadowElevation = 8.dp
                        ) {
                            MahouImage(
                                width = 100.dp,
                                height = 100.dp,
                                contentScale = ContentScale.Inside,
                                resourceImage = R.drawable.expand_circle_right,
                                contentDescription = "More"
                            )
                        }
                    }
                }

            }
        } else {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(50.dp)
            )
        }
    }
}

@Preview(showBackground = true, apiLevel = 34)
@Composable
fun HorizontalScrollerPreview(
) {
    HorizontalScroller(
        modifier = Modifier, title = "Preview", animeList = listOf(
            Anime(
                id = 1,
                mainPicture = MainPicture(
                    large = "https://cdn.myanimelist.net/images/anime/1234.jpg",
                    medium = "https://cdn.myanimelist.net/images/anime/1234.jpg"
                ),
                title = "Test"
            )
        )
    )
}