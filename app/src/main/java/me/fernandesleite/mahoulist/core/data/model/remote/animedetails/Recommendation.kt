package me.fernandesleite.mahoulist.core.data.model.remote.animedetails


import com.squareup.moshi.Json
import me.fernandesleite.mahoulist.core.data.model.remote.common.Anime

data class Recommendation(
    @Json(name = "node")
    val anime: Anime,
    @Json(name = "num_recommendations")
    val numRecommendations: Int
)