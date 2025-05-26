package me.fernandesleite.mahoulist.core.data.model.remote.common


import com.squareup.moshi.Json

data class MainPicture(
    @Json(name = "large")
    val large: String,
    @Json(name = "medium")
    val medium: String
)