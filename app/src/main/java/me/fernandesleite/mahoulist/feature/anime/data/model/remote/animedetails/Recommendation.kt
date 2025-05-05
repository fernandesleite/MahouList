package me.fernandesleite.mahoulist.feature.anime.data.model.remote.animedetails


import com.squareup.moshi.Json
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.common.Anime

data class Recommendation(
    @Json(name = "node")
    val anime: me.fernandesleite.mahoulist.feature.anime.data.model.remote.common.Anime,
    @Json(name = "num_recommendations")
    val numRecommendations: Int
)