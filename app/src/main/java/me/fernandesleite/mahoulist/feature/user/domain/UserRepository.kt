package me.fernandesleite.mahoulist.feature.user.domain

import kotlinx.coroutines.flow.Flow
import me.fernandesleite.mahoulist.core.database.UserEntity
import me.fernandesleite.mahoulist.core.util.Response
import me.fernandesleite.mahoulist.feature.user.data.model.UserModel

interface UserRepository {
    suspend fun getUser(): Flow<Response<UserEntity>>
    suspend fun saveUser(user: UserModel)
    fun updateUser(user: UserModel)
    fun deleteUser(userId: String)
}