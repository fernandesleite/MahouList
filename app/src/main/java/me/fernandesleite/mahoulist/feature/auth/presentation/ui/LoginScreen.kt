package me.fernandesleite.mahoulist.feature.auth.presentation.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.fernandesleite.mahoulist.R
import me.fernandesleite.mahoulist.core.ui.UiState
import me.fernandesleite.mahoulist.feature.auth.presentation.viewmodel.OAuthViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import me.fernandesleite.mahoulist.BuildConfig
import me.fernandesleite.mahoulist.core.ui.theme.theme.MahoulistTheme
import me.fernandesleite.mahoulist.core.util.CoreIntentUtil
import me.fernandesleite.mahoulist.feature.auth.utils.AuthConstants

@Composable
fun LoginScreen(
    viewModel: OAuthViewModel
) {
    val state by viewModel.uiState.collectAsState()
    val user by viewModel.user.collectAsState()
    val codeChallenge by viewModel.codeChallenge.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(state) {
        if (state == UiState.INIT) {
            viewModel.loadUser()
        }
    }

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
                modifier = Modifier.size(350.dp),
                painter = painterResource(R.drawable.mahoulist_title),
                contentDescription = "Remote Image"
            )
            when (state) {
                UiState.CONTENT_NO_USER -> {
                    MahouButton(Modifier, "Log in") {
                        openMALAuth(context, codeChallenge)
                    }
                }
                UiState.CONTENT -> {
                    Text(text = "Continue as")
                    Text(text = user)
                    MahouButton(Modifier, "Log in") {

                    }
                    Text(
                        modifier = Modifier.clickable {
                            openMALAuth(context, codeChallenge)
                        },
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        ),
                        text = AnnotatedString("Log in to another account")
                    )
                }
                UiState.ERROR -> {
                    Text(text = "Error")
                }
                else -> {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

private fun openMALAuth(context: Context, codeChallenge: String) {
    val url = context.getString(
        R.string.auth_url,
        AuthConstants.BASE_URL_AUTH,
        AuthConstants.RESPONSE_TYPE,
        BuildConfig.CLIENT_ID,
        codeChallenge
    )
    CoreIntentUtil.openBrowser(context, url)
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MahoulistTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            LoginScreen(viewModel())
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
