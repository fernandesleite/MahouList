package me.fernandesleite.mahoulist.core.data.model.remote.animedetails


import com.squareup.moshi.Json

data class Picture(
    @Json(name = "large")
    val large: String,
    @Json(name = "medium")
    val medium: String
)