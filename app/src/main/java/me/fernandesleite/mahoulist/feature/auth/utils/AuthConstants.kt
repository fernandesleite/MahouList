package me.fernandesleite.mahoulist.feature.auth.utils

class AuthConstants {
    companion object {
        const val BASE_URL_AUTH = "https://myanimelist.net/v1/oauth2/"
        const val ENDPOINT_TOKEN = "token"
        const val RESPONSE_TYPE = "code"
        const val CODE_QUERY_PARAMETER = "code"
        const val GRANT_TYPE = "authorization_code"
        const val CODE_CHALLENGE_STATE = "codeChallenge"
        const val CODE_CHALLENGE_BYTE_SIZE = 32
        const val EMPTY_STRING = ""
        const val ACCESS_TOKEN = "access_token"
        const val REFRESH_TOKEN = "refresh_token"
        const val UI_STATE = "ui_state"
        const val AUTHORIZATION_HEADER = "Authorization"
    }
}