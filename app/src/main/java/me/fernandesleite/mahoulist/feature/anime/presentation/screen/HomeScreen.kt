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
import me.fernandesleite.mahoulist.core.ui.UiState
import me.fernandesleite.mahoulist.feature.anime.presentation.component.BigImageCarousel
import me.fernandesleite.mahoulist.feature.anime.presentation.component.HorizontalScroller
import me.fernandesleite.mahoulist.feature.anime.presentation.viewmodel.AnimeViewModel

@Composable
fun HomeScreen(
    viewModel: AnimeViewModel,
    navController: NavHostController
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

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        BigImageCarousel(title = "Discover", animeList = currentlyAiringList) {
            // TODO: Navigate to anime details
        }
        HorizontalScroller(
            title = "Currently Airing",
            isLoading = currentlyAiringList.isEmpty(),
            animeList = currentlyAiringList
        ) {
            // TODO: Navigate to anime details
        }
        HorizontalScroller(
            title = "Popular",
            isLoading = currentlyAiringList.isEmpty(),
            animeList = popularList
        ) {
            // TODO: Navigate to anime details
        }
        HorizontalScroller(
            title = "Suggested",
            isLoading = currentlyAiringList.isEmpty(),
            animeList = suggestedList
        ) {
            // TODO: Navigate to anime details
        }
    }

}