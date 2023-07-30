package me.fernandesleite.mahoulist.feature.auth.domain.usecase

import kotlinx.coroutines.flow.Flow
import me.fernandesleite.mahoulist.feature.auth.domain.repository.OAuthRefreshRepository
import javax.inject.Inject

class GetRefreshTokenUseCase @Inject constructor(
    private val repository: OAuthRefreshRepository
    ) {
    suspend operator fun invoke(): Flow<String> = repository.getAuthRefreshToken()
}