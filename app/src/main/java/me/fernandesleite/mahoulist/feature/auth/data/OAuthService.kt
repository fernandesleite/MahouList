package me.fernandesleite.mahoulist.feature.auth.data

import me.fernandesleite.mahoulist.feature.auth.data.model.AccessTokenResponse
import me.fernandesleite.mahoulist.feature.auth.utils.AuthConstants
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface OAuthService {
    @FormUrlEncoded
    @POST(AuthConstants.ENDPOINT_TOKEN)
    suspend fun getAuthAccessToken(
        @Field("client_id") clientId: String,
        @Field("grant_type") grantType: String,
        @Field("code") state: String,
        @Field("code_verifier") codeChallenge: String
    ): AccessTokenResponse

    @FormUrlEncoded
    @POST(AuthConstants.ENDPOINT_TOKEN)
    suspend fun getAuthRefreshToken(
        @Field("client_id") clientId: String,
        @Field("grant_type") grantType: String,
        @Field("refresh_token") refreshToken: String
    ): AccessTokenResponse

}