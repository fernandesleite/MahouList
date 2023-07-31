package me.fernandesleite.mahoulist.feature.auth.domain.usecase

import me.fernandesleite.mahoulist.feature.auth.domain.repository.OAuthRepository
import javax.inject.Inject

class SaveAccessTokenUseCase @Inject constructor(
    private val repository: OAuthRepository
) {

    suspend operator fun invoke(
        clientId: String,
        grantType: String,
        code: String,
        codeChallenge: String
    ) = repository.saveAuthAccessToken(clientId, grantType, code, codeChallenge)
}