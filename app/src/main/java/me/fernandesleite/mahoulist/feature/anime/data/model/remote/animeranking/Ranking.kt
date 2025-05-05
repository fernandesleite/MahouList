package me.fernandesleite.mahoulist.feature.anime.data.model.remote.animeranking


import com.squareup.moshi.Json

data class Ranking(
    @Json(name = "rank")
    val rank: Int
)