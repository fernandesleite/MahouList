package me.fernandesleite.mahoulist.feature.user.data

import me.fernandesleite.mahoulist.feature.user.data.model.UserModel
import retrofit2.http.GET

interface UserService {
    @GET("users/@me")
    suspend fun getUser(): UserModel
}