package me.fernandesleite.mahoulist.feature.anime.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import me.fernandesleite.mahoulist.core.navigation.DefaultTopBar
import me.fernandesleite.mahoulist.core.navigation.Screen
import me.fernandesleite.mahoulist.core.ui.UiState
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.animeranking.AnimeRankingType
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.common.Anime
import me.fernandesleite.mahoulist.feature.anime.presentation.component.BigImageCarousel
import me.fernandesleite.mahoulist.feature.anime.presentation.component.ContentScaffold
import me.fernandesleite.mahoulist.feature.anime.presentation.component.HorizontalScroller
import me.fernandesleite.mahoulist.feature.anime.presentation.viewmodel.AnimeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: AnimeViewModel,
    navController: NavHostController,
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(state) {
        if (state != UiState.CONTENT) {
            viewModel.getHomeContent()
        }
    }
    val suggestedList by viewModel.suggestedList.collectAsState()
    val popularList by viewModel.popularList.collectAsState()
    val currentlyAiringList by viewModel.currentlyAiringList.collectAsState()

    HomeScreenContent(
        modifier,
        { DefaultTopBar(navController = navController) },
        currentlyAiringList,
        popularList,
        suggestedList
    ) { screen ->
        navController.navigate(screen)
    }
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit,
    currentlyAiringList: List<Anime>,
    popularList: List<Anime>,
    suggestedList: List<Anime>,
    navigate: (String) -> Unit,
) {
    ContentScaffold(
        modifier = modifier,
        enableDefaultVerticalPadding = false,
        topBar = topBar
    ) { contentModifier ->
        Column(
            modifier = contentModifier
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BigImageCarousel(
                modifier = Modifier.padding(top = 16.dp),
                title = "Discover",
                animeList = currentlyAiringList
            ) {
                // TODO: Navigate to anime details
            }
            HorizontalScroller(
                title = AnimeRankingType.AIRING.title,
                isLoading = currentlyAiringList.isEmpty(),
                animeList = currentlyAiringList,
                onClickMore = {
                    navigate("${Screen.List.route}/${AnimeRankingType.AIRING.name}")
                }
            )
            HorizontalScroller(
                title = AnimeRankingType.BY_POPULARITY.title,
                isLoading = currentlyAiringList.isEmpty(),
                animeList = popularList,
                onClickMore = {
                    navigate("${Screen.List.route}/${AnimeRankingType.BY_POPULARITY.name}")
                }
            )
            HorizontalScroller(
                modifier = Modifier.padding(bottom = 16.dp),
                title = AnimeRankingType.SUGGESTED.title,
                isLoading = currentlyAiringList.isEmpty(),
                animeList = suggestedList,
                onClickMore = {
                    navigate("${Screen.List.route}/${AnimeRankingType.SUGGESTED.name}")
                }
            )
        }

    }
}