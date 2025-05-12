package me.fernandesleite.mahoulist.core.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MahouNavigationBar(
    navController: NavHostController
) {
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route

    var selected by remember {
        mutableIntStateOf(0)
    }
    val navigationBarItems = listOf(
        NavigationBarRoute(
            name = "Home",
            icon = Icons.Default.Home,
            screen = Screen.Home
        ),
        NavigationBarRoute(
            name = "Seasonal",
            icon = Icons.Default.Explore,
            screen = Screen.Seasonal
        ),
        NavigationBarRoute(
            name = "My List",
            icon = Icons.AutoMirrored.Filled.List,
            screen = Screen.MyList
        )
    )
    val showBottomBar = remember(currentRoute) {
        currentRoute != Screen.Login.route &&
                currentRoute != null &&
                currentRoute != Screen.Search.route
    }
   if(showBottomBar) {
        BottomAppBar {
            NavigationBar {
                navigationBarItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selected == index,
                        onClick = {
                            selected = index
                            navController.navigate(item.screen.route) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.name
                            )
                        },
                        label = {
                            Text(
                                text = item.name,
                                style = MaterialTheme.typography.labelMedium,
                            )
                        }
                    )
                }
            }
        }
    }
}