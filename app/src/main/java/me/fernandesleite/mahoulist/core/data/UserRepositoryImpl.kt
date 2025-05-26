package me.fernandesleite.mahoulist.core.data

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import me.fernandesleite.mahoulist.core.data.database.UserDao
import me.fernandesleite.mahoulist.core.data.database.UserEntity
import me.fernandesleite.mahoulist.core.util.Response
import me.fernandesleite.mahoulist.core.data.model.remote.user.UserModel
import me.fernandesleite.mahoulist.core.data.model.remote.user.toEntity
import me.fernandesleite.mahoulist.core.domain.UserRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val dao: UserDao
) : UserRepository {

    override suspend fun getUser(): Flow<Response<UserEntity>> = flow {

        Log.d("UserRepository", "getUser: called")
        emit(Response.Loading())
        Log.d("UserRepository", "getUser: loading")

        val localUser = dao.getUser()
        if (localUser != null) {
            Log.d("UserRepository", "getUser: local found")
            emit(Response.Success(localUser))
        } else {
            Log.d("UserRepository", "getUser: local not found, fetching from remote")
            fetchAndUpdateUser(this)
        }

    }

    private suspend fun fetchAndUpdateUser(
        flowCollector: FlowCollector<Response<UserEntity>>
    ) {
        Log.d("UserRepository", "getUser: remote")
        try {
            val remoteUser = userService.getUser()
            saveUser(remoteUser)
            dao.getUser()?.let {
                Log.d("UserRepository", "getUser: local found after remote")
                flowCollector.emit(Response.Success(it))
            }
        } catch (e: IOException) {
            Log.d("UserRepository", "getUser: error")

            flowCollector.emit(Response.Error("Network error: ${e.message}"))
        } catch (e: HttpException) {
            Log.d("UserRepository", "getUser:${e.code()}")

            flowCollector.emit(
                Response.NetworkError(
                    e.code(),
                    "Network error: ${e.message}"
                )
            )
        }
    }

    override suspend fun saveUser(user: UserModel) {
        dao.insertUser(user.toEntity())
    }

    override fun updateUser(user: UserModel) {
        TODO("Not yet implemented")
    }

    override fun deleteUser(userId: String) {
        TODO("Not yet implemented")
    }
}