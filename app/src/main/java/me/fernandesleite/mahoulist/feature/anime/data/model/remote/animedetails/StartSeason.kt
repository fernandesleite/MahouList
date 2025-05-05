package me.fernandesleite.mahoulist.feature.anime.data.model.remote.animedetails


import com.squareup.moshi.Json

data class StartSeason(
    @Json(name = "season")
    val season: String,
    @Json(name = "year")
    val year: Int
)