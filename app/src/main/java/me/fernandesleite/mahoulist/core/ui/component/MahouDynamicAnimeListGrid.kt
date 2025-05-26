package me.fernandesleite.mahoulist.core.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.fernandesleite.mahoulist.core.data.model.remote.common.Anime
import me.fernandesleite.mahoulist.core.util.ListViewType

private const val COLUMN_SIZE = 3

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MahouDynamicAnimeListGrid(
    modifier: Modifier,
    selectedViewType: ListViewType,
    onCallMore: () -> Unit,
    onClickContentItem: (Int) -> Unit = {},
    content: List<Anime>,
    showBottomSheet: Boolean = false,
    onChangeSelectedViewType: (ListViewType) -> Unit = {},
    onCloseBottomSheet: (Boolean) -> Unit = {},
) {
    when (selectedViewType) {
        ListViewType.GRID -> {
            GridViewType(modifier, content, onCallMore, onClickContentItem)
        }

        ListViewType.LIST -> {
            ListViewType(modifier, content, onCallMore, onClickContentItem)
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { onCloseBottomSheet(false) },
            containerColor = MaterialTheme.colorScheme.background
        ) {
            val viewOptions = listOf(
                ListViewType.GRID,
                ListViewType.LIST
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SingleChoiceSegmentedButtonRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    viewOptions.forEachIndexed { index, option ->
                        SegmentedButton(
                            colors = SegmentedButtonDefaults.colors(
                                activeContainerColor = MaterialTheme.colorScheme.primary,
                                activeContentColor = MaterialTheme.colorScheme.onPrimary,
                                activeBorderColor = MaterialTheme.colorScheme.primary,
                                inactiveContentColor = MaterialTheme.colorScheme.onSecondary,
                                inactiveContainerColor = MaterialTheme.colorScheme.secondary,
                                inactiveBorderColor = MaterialTheme.colorScheme.secondary
                            ),
                            selected = selectedViewType == option,
                            onClick = { onChangeSelectedViewType(option) },
                            shape = SegmentedButtonDefaults.itemShape(
                                index = index,
                                count = viewOptions.size,
                            )
                        ) {
                            Text(text = option.title)
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun ListViewType(
    modifier: Modifier,
    content: List<Anime>,
    onCallMore: () -> Unit,
    onClickContentItem: (Int) -> Unit,
) {
    val state = rememberLazyListState()

    val callMore by remember {
        derivedStateOf {
            val totalItems = state.layoutInfo.totalItemsCount
            val lastVisibleItemIndex =
                state.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

            lastVisibleItemIndex >= totalItems - 2
        }
    }

    LaunchedEffect(callMore) {
        if (callMore) onCallMore()
    }

    LazyColumn(
        modifier = modifier,
        state = state,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(
            top = 8.dp,
            bottom = 8.dp
        )
    ) {
        itemsIndexed(content) { _, anime ->
            Surface(
                modifier = Modifier
                    .clickable { onClickContentItem(anime.id) },
                shape = RoundedCornerShape(8.dp),
                shadowElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MahouImage(
                        imageUrl = anime.mainPicture.medium,
                        contentDescription = anime.title,
                        contentScale = ContentScale.FillHeight,
                        width = 100.dp,
                        height = 150.dp
                    )
                    Text(
                        textAlign = TextAlign.Start,
                        text = anime.title,
                        fontSize = 18.sp,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

        }
    }
}

@Composable
private fun GridViewType(
    modifier: Modifier,
    content: List<Anime>,
    onCallMore: () -> Unit,
    onClickContentItem: (Int) -> Unit,
) {
    Column(
        modifier = modifier
    ) {

        val state = rememberLazyGridState()

        val callMore by remember {
            derivedStateOf {
                val totalItems = state.layoutInfo.totalItemsCount
                val lastVisibleItemIndex =
                    state.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

                lastVisibleItemIndex >= totalItems - 4
            }
        }

        LaunchedEffect(callMore) {
            if (callMore) onCallMore()
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(COLUMN_SIZE),
            state = state,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(
                top = 8.dp,
                bottom = 8.dp
            )
        ) {
            itemsIndexed(content) { _: Int, item: Anime ->
                Surface(
                    modifier = Modifier
                        .clickable { onClickContentItem(item.id) },
                    shape = RoundedCornerShape(8.dp),
                    shadowElevation = 8.dp
                ) {
                    Column(
                        modifier = Modifier.height(250.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        MahouImage(
                            imageUrl = item.mainPicture.medium,
                            contentDescription = item.title
                        )
                        Text(
                            textAlign = TextAlign.Center,
                            text = item.title,
                            fontSize = 12.sp,
                            lineHeight = 12.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }


    }
}