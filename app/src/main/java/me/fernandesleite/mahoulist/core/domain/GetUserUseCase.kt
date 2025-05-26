package me.fernandesleite.mahoulist.core.domain

import kotlinx.coroutines.flow.Flow
import me.fernandesleite.mahoulist.core.data.database.UserEntity
import me.fernandesleite.mahoulist.core.util.Response
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Flow<Response<UserEntity>> {
        return userRepository.getUser()
    }
}