package me.fernandesleite.mahoulist.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import me.fernandesleite.mahoulist.feature.anime.presentation.screen.HomeScreen
import me.fernandesleite.mahoulist.feature.anime.presentation.screen.MyListScreen
import me.fernandesleite.mahoulist.feature.anime.presentation.screen.SearchScreen
import me.fernandesleite.mahoulist.feature.anime.presentation.screen.SeasonalScreen
import me.fernandesleite.mahoulist.feature.anime.presentation.viewmodel.AnimeViewModel
import me.fernandesleite.mahoulist.feature.auth.presentation.ui.LoginScreen
import me.fernandesleite.mahoulist.feature.auth.presentation.viewmodel.OAuthViewModel

@Composable
fun MahoulistNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    oAuthViewModel: OAuthViewModel? = null
) {
    NavHost(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        navController = navController,
        startDestination = Screen.Home.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(1)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(0)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(0)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(0)
            )
        }
    ) {
        composable(
            route = Screen.Login.route
        ) {
            if (oAuthViewModel != null) {
                LoginScreen(
                    oAuthViewModel,
                    onLogin = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
        composable(
            route = Screen.Home.route
        ) {
            val viewModel = hiltViewModel<AnimeViewModel>()
            HomeScreen(viewModel, navController)
        }
        composable(
            route = Screen.Seasonal.route
        ) {
            val viewModel = hiltViewModel<AnimeViewModel>()
            SeasonalScreen(viewModel, navController)
        }
        composable(
            route = Screen.MyList.route
        ) {
            val viewModel = hiltViewModel<AnimeViewModel>()
            MyListScreen(viewModel, navController)
        }
        composable(
            route = Screen.Search.route
        ) {
            val viewModel = hiltViewModel<AnimeViewModel>()
            SearchScreen(viewModel, navController)
        }
    }
}
