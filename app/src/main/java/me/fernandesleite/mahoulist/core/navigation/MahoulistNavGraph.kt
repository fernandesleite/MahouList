package me.fernandesleite.mahoulist.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import me.fernandesleite.mahoulist.feature.auth.presentation.ui.LoginScreen
import me.fernandesleite.mahoulist.feature.auth.presentation.viewmodel.OAuthViewModel

@Composable
fun MahoulistNavGraph(navController: NavHostController, oAuthViewModel: OAuthViewModel? = null) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            if (oAuthViewModel != null) {
                LoginScreen(oAuthViewModel)
            }
        }
    }
}