package me.fernandesleite.mahoulist.feature.auth.domain.repository

import kotlinx.coroutines.flow.Flow
import me.fernandesleite.mahoulist.core.util.Response
import me.fernandesleite.mahoulist.feature.auth.data.model.AccessTokenResponse

interface OAuthRepository {
    suspend fun saveAuthAccessToken(
        clientId: String,
        grantType: String,
        code: String,
        codeChallenge: String
    ): Flow<Response<AccessTokenResponse>>

    suspend fun getAuthAccessToken(): Flow<String>
}