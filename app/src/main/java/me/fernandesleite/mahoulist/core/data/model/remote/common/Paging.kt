package me.fernandesleite.mahoulist.core.data.model.remote.common


import com.squareup.moshi.Json

data class Paging(
    @Json(name = "next")
    val next: String
)