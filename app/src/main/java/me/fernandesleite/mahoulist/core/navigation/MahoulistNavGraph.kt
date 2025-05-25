package me.fernandesleite.mahoulist.core.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import me.fernandesleite.mahoulist.feature.anime.presentation.screen.ListScreen
import me.fernandesleite.mahoulist.feature.anime.presentation.screen.HomeScreen
import me.fernandesleite.mahoulist.feature.anime.presentation.screen.MyListScreen
import me.fernandesleite.mahoulist.feature.anime.presentation.screen.SearchScreen
import me.fernandesleite.mahoulist.feature.anime.presentation.screen.SeasonalScreen
import me.fernandesleite.mahoulist.feature.anime.presentation.viewmodel.AnimeViewModel
import me.fernandesleite.mahoulist.feature.anime.presentation.viewmodel.ListViewModel
import me.fernandesleite.mahoulist.feature.auth.presentation.ui.LoginScreen
import me.fernandesleite.mahoulist.feature.auth.presentation.viewmodel.OAuthViewModel

@Composable
fun MahoulistNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    oAuthViewModel: OAuthViewModel? = null,
) {
    val homeViewModel = hiltViewModel<AnimeViewModel>()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
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
            HomeScreen(modifier, homeViewModel, navController)
        }
        composable(
            route = Screen.Seasonal.route
        ) {
            val viewModel = hiltViewModel<AnimeViewModel>()
            SeasonalScreen(modifier, viewModel, navController)
        }
        composable(
            route = Screen.MyList.route
        ) {
            val viewModel = hiltViewModel<AnimeViewModel>()
            MyListScreen(modifier, viewModel, navController)
        }
        composable(
            route = Screen.Search.route
        ) {
            SearchScreen(homeViewModel, navController)
        }
        composable(
            route = "${Screen.List.route}/{listType}"
        ) {
            val listType = it.arguments?.getString("listType")
            val viewModel = hiltViewModel<ListViewModel>()
            ListScreen(viewModel, navController, listType)
        }
    }
}
