package me.fernandesleite.mahoulist.core.navigation.topbar

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
import androidx.navigation.NavHostController
import me.fernandesleite.mahoulist.core.navigation.Screen

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DefaultTopBar(navController: NavHostController) {
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
