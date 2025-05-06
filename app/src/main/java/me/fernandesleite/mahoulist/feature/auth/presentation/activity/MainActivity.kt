package me.fernandesleite.mahoulist.feature.auth.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import me.fernandesleite.mahoulist.core.navigation.MahoulistNavGraph
import me.fernandesleite.mahoulist.core.ui.theme.theme.MahoulistTheme
import me.fernandesleite.mahoulist.feature.auth.presentation.viewmodel.OAuthViewModel
import me.fernandesleite.mahoulist.feature.auth.utils.AuthConstants

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val oAuthViewModel by viewModels<OAuthViewModel>()
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            MahoulistTheme {
                MahoulistNavGraph(navController = navController, oAuthViewModel = oAuthViewModel)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        intent.data?.let { uri ->
            uri.getQueryParameter(AuthConstants.CODE_QUERY_PARAMETER)?.let { responseType ->
                oAuthViewModel.getAuthAccessToken(responseType)
            }
        }
    }
}