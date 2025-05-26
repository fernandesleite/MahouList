package me.fernandesleite.mahoulist.core.data.model.remote.suggestedanime


import com.squareup.moshi.Json
import me.fernandesleite.mahoulist.core.data.model.remote.common.AnimeWrapper
import me.fernandesleite.mahoulist.core.data.model.remote.common.Paging

data class SuggestedAnimeModel(
    @Json(name = "data")
    val `data`: List<AnimeWrapper>,
    @Json(name = "paging")
    val paging: Paging
)