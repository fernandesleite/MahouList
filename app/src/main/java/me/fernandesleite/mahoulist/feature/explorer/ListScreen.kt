package me.fernandesleite.mahoulist.feature.explorer

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import me.fernandesleite.mahoulist.core.data.model.remote.animeranking.AnimeRankingType
import me.fernandesleite.mahoulist.core.data.model.remote.common.Anime
import me.fernandesleite.mahoulist.core.data.model.remote.common.MainPicture
import me.fernandesleite.mahoulist.core.navigation.topbar.ListTopBar
import me.fernandesleite.mahoulist.core.ui.component.ContentScaffold
import me.fernandesleite.mahoulist.core.util.ListViewType
import me.fernandesleite.mahoulist.core.ui.component.MahouDynamicAnimeListGrid

@Composable
fun ListScreen(
    viewModel: ListViewModel,
    navHostController: NavHostController,
    listType: String?,
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedViewType by remember { mutableStateOf(ListViewType.GRID) }

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
        content = content,
        selectedViewType = selectedViewType,
        showBottomSheet = showBottomSheet,
        onCloseBottomSheet = { showBottomSheet = it },
        onChangeSelectedViewType = { selectedViewType = it },
        onCallMore = {
            viewModel.getListContent(
                source = AnimeRankingType.valueOf(
                    listType ?: "ALL"
                )
            )
        },
        onClickContentItem = { animeId ->
            Log.d("MahouDynamicAnimeListGrid", "ListScreen: $animeId")
        }
    )
}

@Composable
private fun ListScreenContent(
    topBar: @Composable () -> Unit,
    content: List<Anime>,
    selectedViewType: ListViewType = ListViewType.GRID,
    showBottomSheet: Boolean,
    onCloseBottomSheet: (Boolean) -> Unit,
    onChangeSelectedViewType: (ListViewType) -> Unit = {},
    onCallMore: () -> Unit = {},
    onClickContentItem: (Int) -> Unit = {},
) {

    ContentScaffold(
        enableDefaultVerticalPadding = false,
        topBar = topBar
    ) { modifier ->
        MahouDynamicAnimeListGrid(
            modifier,
            selectedViewType,
            onCallMore,
            onClickContentItem,
            content,
            showBottomSheet,
            onChangeSelectedViewType,
            onCloseBottomSheet
        )
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
        content = listOf(anime, anime, anime, anime, anime, anime, anime, anime),
        showBottomSheet = false,
        onCloseBottomSheet = { },
        onCallMore = {

        })
}