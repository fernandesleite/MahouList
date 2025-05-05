package me.fernandesleite.mahoulist.feature.anime.data.model.remote.animeranking


import com.squareup.moshi.Json
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.common.Anime

data class RankingData(
    @Json(name = "node")
    val anime: Anime,
    @Json(name = "ranking")
    val ranking: Ranking
)