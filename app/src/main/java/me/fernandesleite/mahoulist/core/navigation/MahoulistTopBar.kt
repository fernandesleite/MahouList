package me.fernandesleite.mahoulist.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MahoulistTopBar(
    navController: NavHostController
) {
    val currentDestination by navController.currentBackStackEntryAsState()
    val currentRoute = currentDestination?.destination?.route

    val showTopBar = remember(currentRoute) {
        currentRoute != Screen.Login.route &&
                currentRoute != null &&
                currentRoute != Screen.Search.route
    }

    if (showTopBar) {
        DefaultTopBar(navController)
    }
    if (currentRoute == Screen.Search.route) {
        SearchBarTopBar(navController)
    }
}

@Composable
private fun SearchBarTopBar(navController: NavHostController) {
//    val backStackEntry by navController.currentBackStackEntryAsState()
//    val searchEntry = remember(backStackEntry) {
//        navController.getBackStackEntry(Screen.Search.route)
//    }
//    val viewModel = hiltViewModel<AnimeViewModel>(searchEntry)
//    val search by viewModel.searchText.collectAsState()
//
//
//    TextField(value = search, onValueChange = {
//        viewModel.sendText(it)
//    })
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DefaultTopBar(navController: NavHostController) {
    TopAppBar(
        title = { Text(text = "Mahoulist") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        actions = {
            IconButton(onClick = { navController.navigate(Screen.Search.route) }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        },
    )
}
