package me.fernandesleite.mahoulist.feature.auth.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.fernandesleite.mahoulist.BuildConfig
import me.fernandesleite.mahoulist.core.extension.NetworkExtensions.onErrorDo
import me.fernandesleite.mahoulist.core.ui.UiState
import me.fernandesleite.mahoulist.feature.auth.domain.usecase.SaveAccessTokenUseCase
import me.fernandesleite.mahoulist.feature.auth.utils.AuthConstants
import java.security.SecureRandom
import java.util.Base64
import javax.inject.Inject

@HiltViewModel
class OAuthViewModel @Inject constructor(
    private val getAuthAccessToken: SaveAccessTokenUseCase,
    private val state: SavedStateHandle,
) : ViewModel() {

    private val _codeChallenge = MutableStateFlow(AuthConstants.EMPTY_STRING)
    val codeChallenge: StateFlow<String> = _codeChallenge

    private val _uiState = MutableStateFlow(UiState.INIT)
    val uiState: StateFlow<UiState> = _uiState

    fun getAuthAccessToken(code: String) {
        viewModelScope.launch {

            getAuthAccessToken(
                BuildConfig.CLIENT_ID,
                AuthConstants.GRANT_TYPE,
                code,
                codeChallenge.value
            ).collect {
                it.onErrorDo {
                    _uiState.value = UiState.ERROR
                }
            }

        }
    }

    fun getCodeChallenge() {
        if (state.get<String>(AuthConstants.CODE_CHALLENGE_STATE) == null) {
            val secureRandom = SecureRandom()
            val codeVerifier = ByteArray(AuthConstants.CODE_CHALLENGE_BYTE_SIZE)
            secureRandom.nextBytes(codeVerifier)
            val randomCode = Base64.getUrlEncoder().withoutPadding().encodeToString(codeVerifier)
            state[AuthConstants.CODE_CHALLENGE_STATE] = randomCode
            _codeChallenge.value = randomCode
        }
    }

}