package me.fernandesleite.mahoulist.feature.anime.data.model.remote.common


import com.squareup.moshi.Json

data class AnimeWrapper(
    @Json(name = "node")
    val anime: Anime
)