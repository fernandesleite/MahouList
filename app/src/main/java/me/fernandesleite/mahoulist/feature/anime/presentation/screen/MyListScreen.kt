package me.fernandesleite.mahoulist.feature.anime.presentation.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import me.fernandesleite.mahoulist.core.navigation.DefaultTopBar
import me.fernandesleite.mahoulist.feature.anime.presentation.component.ContentScaffold

@Composable
fun MyListScreen(
    modifier: Modifier,
    viewModel: ViewModel,
    navController: NavHostController
) {
    ContentScaffold(
        modifier = modifier,
        topBar = {
            DefaultTopBar(navController = navController)
        }
    ) { contentModifier ->
        Text(modifier = contentModifier, text = "My List")
    }
}