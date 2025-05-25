package me.fernandesleite.mahoulist.feature.anime.presentation.screen

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import me.fernandesleite.mahoulist.core.navigation.ListTopBar
import me.fernandesleite.mahoulist.core.ui.UiState
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.animeranking.AnimeRankingType
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.common.Anime
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.common.MainPicture
import me.fernandesleite.mahoulist.feature.anime.presentation.component.ContentScaffold
import me.fernandesleite.mahoulist.feature.anime.presentation.viewmodel.ListViewModel

private const val COLUMN_SIZE = 3

@Composable
fun ListScreen(
    viewModel: ListViewModel,
    navHostController: NavHostController,
    listType: String?,
    animeList: List<Anime> = listOf(),
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    val content by viewModel.content.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getListContent(AnimeRankingType.valueOf(listType ?: "ALL"))
    }

    ListScreenContent(
        topBar = {
            ListTopBar(
                toggleButtonSheet = { showBottomSheet = !showBottomSheet },
                title = (AnimeRankingType.valueOf(listType ?: "ALL").title),
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        },
        uiState = uiState,
        content = content,
        showBottomSheet = showBottomSheet,
        onCloseBottomSheet = { showBottomSheet = it },
        onCallMore = {
            viewModel.getListContent(
                source = AnimeRankingType.valueOf(
                    listType ?: "ALL"
                )
            )
        })
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun ListScreenContent(
    topBar: @Composable () -> Unit,
    uiState: UiState,
    content: List<Anime>,
    showBottomSheet: Boolean,
    onCloseBottomSheet: (Boolean) -> Unit,
    onCallMore: () -> Unit = {},
) {
    var selectedViewType by remember { mutableStateOf(ListViewType.GRID) }

    ContentScaffold(
        enableDefaultVerticalPadding = false,
        topBar = topBar
    ) { modifier ->
        when (selectedViewType) {
            ListViewType.GRID -> {
                GridViewType(modifier, onCallMore, uiState, content)
            }

            ListViewType.LIST -> {
                ListViewType(modifier, onCallMore, uiState, content)
            }
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
                            onClick = { selectedViewType = option },
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
    onCallMore: () -> Unit,
    uiState: UiState,
    content: List<Anime>,
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
                    .clickable { },
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
    onCallMore: () -> Unit,
    uiState: UiState,
    content: List<Anime>,
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

        if (uiState == UiState.ERROR) {
            // Handle error state
        } else if (uiState == UiState.CONTENT) {
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
                            .clickable { },
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
}


@Preview(showBackground = true, apiLevel = 34)
@Composable
fun ListScreenPreview() {
    val anime = Anime(
        id = 1,
        title = "Attack on Titan",
        mainPicture = MainPicture(
            large = "https://example.com/large.jpg",
            medium = "https://example.com/medium.jpg"
        )
    )
    ListScreenContent(
        topBar = {
            ListTopBar(
                toggleButtonSheet = { },
                title = "List",
            ) {

            }

        },
        uiState = UiState.CONTENT,
        content = listOf(anime, anime, anime, anime, anime, anime, anime, anime),
        showBottomSheet = true,
        onCloseBottomSheet = { },
        onCallMore = {

        })
}

enum class ListViewType(val title: String) {
    GRID("Grid View"),
    LIST("List View")
}
