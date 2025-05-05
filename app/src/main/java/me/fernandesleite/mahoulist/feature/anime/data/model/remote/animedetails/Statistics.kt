package me.fernandesleite.mahoulist.feature.anime.data.model.remote.animedetails


import com.squareup.moshi.Json

data class Statistics(
    @Json(name = "num_list_users")
    val numListUsers: Int,
    @Json(name = "status")
    val status: me.fernandesleite.mahoulist.feature.anime.data.model.remote.animedetails.Status
)