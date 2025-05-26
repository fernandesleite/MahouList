package me.fernandesleite.mahoulist.core.data.model.remote.animedetails


import com.squareup.moshi.Json

data class Statistics(
    @Json(name = "num_list_users")
    val numListUsers: Int,
    @Json(name = "status")
    val status: Status
)