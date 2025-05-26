package me.fernandesleite.mahoulist.core.domain

import kotlinx.coroutines.flow.Flow
import me.fernandesleite.mahoulist.core.data.database.UserEntity
import me.fernandesleite.mahoulist.core.util.Response
import me.fernandesleite.mahoulist.core.data.model.remote.user.UserModel

interface UserRepository {
    suspend fun getUser(): Flow<Response<UserEntity>>
    suspend fun saveUser(user: UserModel)
    fun updateUser(user: UserModel)
    fun deleteUser(userId: String)
}