package me.fernandesleite.mahoulist.core.navigation

sealed class Screen(val route: String, val name: String? = null) {
    data object Login : Screen("login")
    data object Home : Screen("home")
    data object Seasonal : Screen("seasonal")
    data object MyList : Screen("my_list")
    data object Search : Screen("search")
    data object List : Screen("list")
}
