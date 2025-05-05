package me.fernandesleite.mahoulist.feature.anime.data.model.remote.common


import com.squareup.moshi.Json

data class Paging(
    @Json(name = "next")
    val next: String
)