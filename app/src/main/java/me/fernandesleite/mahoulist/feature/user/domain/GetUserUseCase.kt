package me.fernandesleite.mahoulist.feature.user.domain

import kotlinx.coroutines.flow.Flow
import me.fernandesleite.mahoulist.core.database.UserEntity
import me.fernandesleite.mahoulist.core.util.Response

class GetUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Flow<Response<UserEntity>> {
        return userRepository.getUser()
    }
}