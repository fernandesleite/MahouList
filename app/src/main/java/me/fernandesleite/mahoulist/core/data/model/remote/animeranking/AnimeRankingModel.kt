package me.fernandesleite.mahoulist.core.data.model.remote.animeranking


import com.squareup.moshi.Json
import me.fernandesleite.mahoulist.core.data.model.remote.common.Paging

data class AnimeRankingModel(
    @Json(name = "data")
    val `data`: List<RankingData>,
    @Json(name = "paging")
    val paging: Paging
)