package me.fernandesleite.mahoulist.feature.auth.domain.usecase

import me.fernandesleite.mahoulist.feature.auth.domain.repository.OAuthRefreshRepository
import javax.inject.Inject

class SaveRefreshTokenUseCase @Inject constructor(
    private val repository: OAuthRefreshRepository
) {

    suspend operator fun invoke(
        clientId: String,
        grantType: String,
        refreshToken: String
    ) = repository.saveAuthRefreshToken(clientId, grantType, refreshToken)
}