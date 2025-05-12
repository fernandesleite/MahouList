package me.fernandesleite.mahoulist.feature.anime.presentation.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

@Composable
fun MyListScreen(
    viewModel: ViewModel,
    navController: NavHostController
) {
    Text(text = "My List")
}