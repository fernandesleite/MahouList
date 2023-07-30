package me.fernandesleite.mahoulist.feature.auth.domain.usecase

import me.fernandesleite.mahoulist.feature.auth.domain.repository.OAuthRepository
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val repository: OAuthRepository
) {

    suspend operator fun invoke() = repository.getAuthAccessToken()
}