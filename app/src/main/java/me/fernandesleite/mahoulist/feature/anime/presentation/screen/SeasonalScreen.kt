package me.fernandesleite.mahoulist.feature.anime.presentation.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

@Composable
fun SeasonalScreen(
    modifier: Modifier = Modifier,
    viewModel: ViewModel,
    navController: NavHostController
) {
    Text(text = "Seasonal")
}