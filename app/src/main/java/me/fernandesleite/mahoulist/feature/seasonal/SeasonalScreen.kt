package me.fernandesleite.mahoulist.feature.seasonal

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import me.fernandesleite.mahoulist.core.navigation.topbar.DefaultTopBar
import me.fernandesleite.mahoulist.core.ui.component.ContentScaffold

@Composable
fun SeasonalScreen(
    modifier: Modifier = Modifier,
    viewModel: ViewModel,
    navController: NavHostController
) {
    ContentScaffold(
        modifier = modifier,
        topBar = {
            DefaultTopBar(navController = navController)
        }
    ) { contentModifier ->
        Text(modifier = contentModifier, text = "Seasonal Screen")
    }
}