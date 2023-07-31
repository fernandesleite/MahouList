package me.fernandesleite.mahoulist.feature.auth.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import me.fernandesleite.mahoulist.BuildConfig
import me.fernandesleite.mahoulist.R
import me.fernandesleite.mahoulist.core.ui.theme.theme.MahoulistTheme
import me.fernandesleite.mahoulist.core.util.CoreIntentUtil
import me.fernandesleite.mahoulist.feature.auth.presentation.viewmodel.OAuthViewModel
import me.fernandesleite.mahoulist.feature.auth.utils.AuthConstants

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<OAuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCodeChallenge()
        setContent {
            MahoulistTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                    Row {
                        Button(onClick = {
                            lifecycleScope.launch {
                                viewModel.codeChallenge.collect { codeChallenge ->
                                    openMALAuth(codeChallenge)
                                }
                            }
                        }) {
                            Text(text = "Redirect")
                        }
                        Button(onClick = {
                        }) {
                            Text(text = "Call Service")
                        }
                    }

                }
            }
        }
    }

    private fun openMALAuth(codeChallenge: String) {
        val url = getString(
            R.string.auth_url,
            AuthConstants.BASE_URL_AUTH,
            AuthConstants.RESPONSE_TYPE,
            BuildConfig.CLIENT_ID,
            codeChallenge
        )
        CoreIntentUtil.openBrowser(this, url)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.data?.let { uri ->
            uri.getQueryParameter(AuthConstants.CODE_QUERY_PARAMETER)?.let {
                    responseType -> viewModel.getAuthAccessToken(responseType)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MahoulistTheme {
        Greeting("Android")
    }
}