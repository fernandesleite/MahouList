package me.fernandesleite.mahoulist.feature.auth.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import me.fernandesleite.mahoulist.BuildConfig
import me.fernandesleite.mahoulist.R
import me.fernandesleite.mahoulist.core.ui.UiState
import me.fernandesleite.mahoulist.core.ui.theme.theme.MahoulistTheme
import me.fernandesleite.mahoulist.core.util.CoreIntentUtil
import me.fernandesleite.mahoulist.feature.anime.presentation.AnimeViewModel
import me.fernandesleite.mahoulist.feature.auth.presentation.viewmodel.OAuthViewModel
import me.fernandesleite.mahoulist.feature.auth.utils.AuthConstants

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<OAuthViewModel>()
    private val animeViewModel: AnimeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCodeChallenge()
        setContent {
            MahoulistTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LaunchedEffect(Unit) {
                        viewModel.loadUser()
                    }
                    val user by viewModel.user.collectAsState()
                    val state = viewModel.uiState.collectAsState().value

                    LoginScreen(
                        modifier = Modifier,
                        user = user,
                        state = state,
                        onLoginClick = {
                            animeViewModel.getTest()
                        },
                        onLoginAnotherAccountClick = {
                            redirectToLoginMAL()
                        }
                    )
                }
            }
        }
    }

    private fun redirectToLoginMAL() {
        lifecycleScope.launch {
            viewModel.codeChallenge.collect { codeChallenge ->
                openMALAuth(codeChallenge)
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
            uri.getQueryParameter(AuthConstants.CODE_QUERY_PARAMETER)?.let { responseType ->
                viewModel.getAuthAccessToken(responseType)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MahoulistTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            LoginScreen(
                onLoginClick = {},
                user = "User",
                state = UiState.LOADING,
                onLoginAnotherAccountClick = {}
            )
        }
    }
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    user: String,
    state: UiState,
    onLoginClick: () -> Unit = {},
    onLoginAnotherAccountClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                modifier = Modifier
                    .size(350.dp),
                painter = painterResource(R.drawable.mahoulist_title),
                contentDescription = "Remote Image"
            )
            if (UiState.CONTENT_NO_USER == state) {
                MahouButton(Modifier, "Log in") {
                    onLoginAnotherAccountClick()
                }
            } else if (UiState.CONTENT == state) {
                Text(text = "Continue as")
                Text(text = user)
                MahouButton(Modifier, "Log in") {
                    onLoginClick()
                }
                ClickableText(
                    modifier = Modifier,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                    ),
                    text = AnnotatedString("Log in to another account")
                ) {
                    onLoginAnotherAccountClick()
                }
            } else if (UiState.ERROR == state) {
                Text(text = "Error")
            } else {
                CircularProgressIndicator()
            }


        }
    }
}

@Composable
fun MahouButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit
) {
    val buttonColors = buttonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        disabledContainerColor = MaterialTheme.colorScheme.primary,
        disabledContentColor = MaterialTheme.colorScheme.onPrimary
    )
    Button(
        modifier = modifier
            .heightIn(min = 24.dp)
            .widthIn(min = 200.dp),
        onClick = onClick,
        colors = buttonColors,
        shape = MaterialTheme.shapes.extraSmall,
    ) {
        Text(text = text)
    }
}