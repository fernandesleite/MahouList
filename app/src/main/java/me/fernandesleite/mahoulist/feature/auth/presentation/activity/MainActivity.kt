package me.fernandesleite.mahoulist.feature.auth.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import me.fernandesleite.mahoulist.core.navigation.MahouNavigationBar
import me.fernandesleite.mahoulist.core.navigation.MahoulistNavGraph
import me.fernandesleite.mahoulist.core.navigation.MahoulistTopBar
import me.fernandesleite.mahoulist.core.ui.theme.theme.MahoulistTheme
import me.fernandesleite.mahoulist.feature.auth.presentation.viewmodel.OAuthViewModel
import me.fernandesleite.mahoulist.feature.auth.utils.AuthConstants

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val oAuthViewModel by viewModels<OAuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MahoulistTheme {
                Scaffold(
                    topBar = {
                        MahoulistTopBar(navController)
                    },
                    bottomBar = {
                        MahouNavigationBar(navController)
                    }
                ) { innerPadding ->

                    MahoulistNavGraph(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        oAuthViewModel = oAuthViewModel
                    )


                }
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