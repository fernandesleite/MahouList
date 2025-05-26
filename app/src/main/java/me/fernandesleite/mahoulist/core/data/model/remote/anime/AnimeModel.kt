package me.fernandesleite.mahoulist.core.data.model.remote.anime


import com.squareup.moshi.Json
import me.fernandesleite.mahoulist.core.data.model.remote.common.AnimeWrapper
import me.fernandesleite.mahoulist.core.data.model.remote.common.Paging

data class AnimeModel(
    @Json(name = "data")
    val animeList: List<AnimeWrapper>,
    @Json(name = "paging")
    val paging: Paging
)