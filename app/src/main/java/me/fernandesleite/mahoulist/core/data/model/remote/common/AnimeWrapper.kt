package me.fernandesleite.mahoulist.core.data.model.remote.common


import com.squareup.moshi.Json

data class AnimeWrapper(
    @Json(name = "node")
    val anime: Anime
)