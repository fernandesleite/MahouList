package me.fernandesleite.mahoulist.core.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
}
