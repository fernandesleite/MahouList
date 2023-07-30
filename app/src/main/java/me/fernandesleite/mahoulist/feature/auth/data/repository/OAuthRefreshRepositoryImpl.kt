package me.fernandesleite.mahoulist.feature.auth.data.repository

import android.content.SharedPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import me.fernandesleite.mahoulist.core.util.Response
import me.fernandesleite.mahoulist.core.di.qualifier.NoTokenRefresh
import me.fernandesleite.mahoulist.core.extension.NetworkExtensions.getResponse
import me.fernandesleite.mahoulist.feature.auth.data.OAuthService
import me.fernandesleite.mahoulist.feature.auth.data.model.AccessTokenResponse
import me.fernandesleite.mahoulist.feature.auth.domain.repository.OAuthRefreshRepository
import me.fernandesleite.mahoulist.feature.auth.utils.AuthConstants
import javax.inject.Inject

class OAuthRefreshRepositoryImpl @Inject constructor(
    @NoTokenRefresh private val api: OAuthService,
    private val sharedPreferences: SharedPreferences
) : OAuthRefreshRepository {
    override suspend fun saveAuthRefreshToken(
        clientId: String,
        grantType: String,
        refreshToken: String
    ): Flow<Response<AccessTokenResponse>> =
        flow { emit(api.getAuthRefreshToken(clientId, grantType, refreshToken)) }.getResponse().map { response ->
            if (response is Response.Success) {
                saveTokenToSharedPreferences(response)
            }
            return@map response
        }



    override suspend fun getAuthRefreshToken(): Flow<String> {
        return flow {
            sharedPreferences
                .getString(
                    AuthConstants.REFRESH_TOKEN,
                    AuthConstants.EMPTY_STRING
                )
                ?.let { emit(it) }
        }
    }

    private fun saveTokenToSharedPreferences(response: Response<AccessTokenResponse>) {
        val editor = sharedPreferences.edit()
        response.data?.let {
            editor.putString(AuthConstants.ACCESS_TOKEN, it.accessToken)
            editor.putString(AuthConstants.REFRESH_TOKEN, it.refreshToken)
            editor.apply()
        }
    }

}