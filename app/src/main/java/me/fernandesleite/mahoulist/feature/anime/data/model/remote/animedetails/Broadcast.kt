package me.fernandesleite.mahoulist.feature.anime.data.model.remote.animedetails


import com.squareup.moshi.Json

data class Broadcast(
    @Json(name = "day_of_the_week")
    val dayOfTheWeek: String,
    @Json(name = "start_time")
    val startTime: String
)