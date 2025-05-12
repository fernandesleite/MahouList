package me.fernandesleite.mahoulist.feature.anime.presentation.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import me.fernandesleite.mahoulist.feature.anime.presentation.viewmodel.AnimeViewModel

@Composable
fun HomeScreen(
    viewModel: AnimeViewModel,
    navController: NavHostController
) {
    viewModel.getTest()
}