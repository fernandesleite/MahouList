package me.fernandesleite.mahoulist.core.data.model.remote.animedetails


import com.squareup.moshi.Json

data class Status(
    @Json(name = "completed")
    val completed: String,
    @Json(name = "dropped")
    val dropped: String,
    @Json(name = "on_hold")
    val onHold: String,
    @Json(name = "plan_to_watch")
    val planToWatch: String,
    @Json(name = "watching")
    val watching: String
)