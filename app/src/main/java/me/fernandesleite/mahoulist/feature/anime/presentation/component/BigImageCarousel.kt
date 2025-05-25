package me.fernandesleite.mahoulist.feature.anime.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.common.Anime
import me.fernandesleite.mahoulist.feature.anime.presentation.screen.MahouImage

@Composable
fun BigImageCarousel(
    modifier: Modifier = Modifier,
    title: String,
    animeList: List<Anime> = listOf(),
    onClickItem: (Anime) -> Unit = {},
) {
    val state = rememberPagerState(0) {
        animeList.count()
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Start),
            text = title,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.titleLarge,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.TopCenter
        ) {
            HorizontalPager(
                state = state
            ) { i ->
                val carouselItem = animeList[i]
                Surface(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .height(150.dp)
                        .shadow(8.dp, RoundedCornerShape(16.dp))
                        .clickable {
                            onClickItem(animeList[state.currentPage])
                        }
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        MahouImage(
                            imageUrl = carouselItem.mainPicture.large,
                            contentDescription = carouselItem.title
                        )
                        Text(
                            text = carouselItem.title,
                            maxLines = 2,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                        )
                    }
                }
            }

        }
        DotsIndicator(
            modifier = Modifier
                .padding(top = 8.dp),
            state.pageCount,
            state.currentPage
        )
    }

}

@Preview(showBackground = true)
@Composable
fun IndicatorDot(
    color: Color = MaterialTheme.colorScheme.primary,
) {
    Box(
        modifier = Modifier
            .size(10.dp)
            .clip(CircleShape)
            .background(color),
        content = {}
    )
}

@Preview(showBackground = true)
@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int = 0,
    selectedDot: Int = 0,
) {
    val selectedColor: Color = MaterialTheme.colorScheme.primary
    val unSelectedColor: Color = MaterialTheme.colorScheme.secondary
    LazyRow(
        modifier = modifier
            .wrapContentSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalArrangement = Arrangement.spacedBy(4.dp)

    ) {
        items(totalDots) { currentDot ->
            if (currentDot == selectedDot) {
                IndicatorDot(color = selectedColor)
            } else
                IndicatorDot(color = unSelectedColor)
        }
    }
}