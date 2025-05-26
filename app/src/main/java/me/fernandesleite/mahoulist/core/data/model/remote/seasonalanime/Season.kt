package me.fernandesleite.mahoulist.core.data.model.remote.seasonalanime


import com.squareup.moshi.Json

data class Season(
    @Json(name = "season")
    val season: String,
    @Json(name = "year")
    val year: Int
)