package me.fernandesleite.mahoulist.core.data

import me.fernandesleite.mahoulist.core.data.model.remote.user.UserModel
import retrofit2.http.GET

interface UserService {
    @GET("users/@me")
    suspend fun getUser(): UserModel
}