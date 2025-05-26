package me.fernandesleite.mahoulist.core.data.model.remote.animedetails


import com.squareup.moshi.Json

data class Studio(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String
)