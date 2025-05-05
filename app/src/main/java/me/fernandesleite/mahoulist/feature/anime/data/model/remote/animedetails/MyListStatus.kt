package me.fernandesleite.mahoulist.feature.anime.data.model.remote.animedetails


import com.squareup.moshi.Json

data class MyListStatus(
    @Json(name = "is_rewatching")
    val isRewatching: Boolean,
    @Json(name = "num_episodes_watched")
    val numEpisodesWatched: Int,
    @Json(name = "score")
    val score: Int,
    @Json(name = "status")
    val status: String,
    @Json(name = "updated_at")
    val updatedAt: String
)