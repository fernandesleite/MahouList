package me.fernandesleite.mahoulist.feature.auth.domain.repository

import kotlinx.coroutines.flow.Flow
import me.fernandesleite.mahoulist.core.util.Response
import me.fernandesleite.mahoulist.feature.auth.data.model.AccessTokenResponse

interface OAuthRefreshRepository {

    suspend fun saveAuthRefreshToken(
        clientId: String,
        grantType: String,
        refreshToken: String
    ): Flow<Response<AccessTokenResponse>>

    suspend fun getAuthRefreshToken(): Flow<String>
}