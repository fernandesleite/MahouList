package me.fernandesleite.mahoulist.feature.anime.data.model.remote.animedetails


import com.squareup.moshi.Json

data class AlternativeTitles(
    @Json(name = "en")
    val en: String,
    @Json(name = "ja")
    val ja: String,
    @Json(name = "synonyms")
    val synonyms: List<String>
)