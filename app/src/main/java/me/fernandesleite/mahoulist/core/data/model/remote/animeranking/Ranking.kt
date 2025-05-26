package me.fernandesleite.mahoulist.core.data.model.remote.animeranking


import com.squareup.moshi.Json

data class Ranking(
    @Json(name = "rank")
    val rank: Int
)