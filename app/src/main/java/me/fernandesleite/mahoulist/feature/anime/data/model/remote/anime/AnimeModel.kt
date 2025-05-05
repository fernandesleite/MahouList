package me.fernandesleite.mahoulist.feature.anime.data.model.remote.anime


import com.squareup.moshi.Json
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.common.AnimeWrapper
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.common.Paging

data class AnimeModel(
    @Json(name = "data")
    val animeList: List<AnimeWrapper>,
    @Json(name = "paging")
    val paging: Paging
)