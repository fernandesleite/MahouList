package me.fernandesleite.mahoulist.feature.anime.data.model.remote.animedetails


import com.squareup.moshi.Json
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.common.Anime

data class RelatedAnime(
    @Json(name = "node")
    val anime: me.fernandesleite.mahoulist.feature.anime.data.model.remote.common.Anime,
    @Json(name = "relation_type")
    val relationType: String,
    @Json(name = "relation_type_formatted")
    val relationTypeFormatted: String
)