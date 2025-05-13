package me.fernandesleite.mahoulist.feature.anime.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import me.fernandesleite.mahoulist.core.ui.UiState
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
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        HorizontalScroller(
            title = "Currently Airing",
            isLoading = currentlyAiringList.isEmpty(),
            animeList = currentlyAiringList
        )
        HorizontalScroller(
            title = "Popular",
            isLoading = currentlyAiringList.isEmpty(),
            animeList = popularList
        )
        HorizontalScroller(
            title = "Suggested",
            isLoading = currentlyAiringList.isEmpty(),
            animeList = suggestedList
        )
    }

}