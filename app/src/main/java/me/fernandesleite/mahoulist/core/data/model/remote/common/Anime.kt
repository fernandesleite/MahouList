package me.fernandesleite.mahoulist.core.data.model.remote.common


import com.squareup.moshi.Json

data class Anime(
    @Json(name = "id")
    val id: Int,
    @Json(name = "main_picture")
    val mainPicture: MainPicture,
    @Json(name = "title")
    val title: String
)