package me.fernandesleite.mahoulist.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val location: String,
    val joinedAt: String
)