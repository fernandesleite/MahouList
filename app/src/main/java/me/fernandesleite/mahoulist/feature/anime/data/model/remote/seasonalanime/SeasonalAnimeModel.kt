package me.fernandesleite.mahoulist.feature.anime.data.model.remote.seasonalanime


import com.squareup.moshi.Json
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.common.AnimeWrapper
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.common.Paging

data class SeasonalAnimeModel(
    @Json(name = "data")
    val `data`: List<AnimeWrapper>,
    @Json(name = "paging")
    val paging: Paging,
    @Json(name = "season")
    val season: Season
)