package me.fernandesleite.mahoulist.feature.user.data.model


import com.squareup.moshi.Json
import me.fernandesleite.mahoulist.core.database.UserEntity

data class UserModel(
    @Json(name = "id")
    val id: Int,
    @Json(name = "joined_at")
    val joinedAt: String,
    @Json(name = "location")
    val location: String,
    @Json(name = "name")
    val name: String
)

fun UserModel.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        location = location,
        joinedAt = joinedAt
    )
}