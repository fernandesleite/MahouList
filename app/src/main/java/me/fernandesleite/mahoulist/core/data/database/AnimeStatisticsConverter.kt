package me.fernandesleite.mahoulist.core.data.database

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import me.fernandesleite.mahoulist.core.data.model.remote.user.AnimeStatistics

class AnimeStatisticsConverter {
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val jsonAdapter = moshi.adapter(AnimeStatistics::class.java)

    @TypeConverter
    fun fromAnimeStatistics(animeStatistics: AnimeStatistics): String {
        return jsonAdapter.toJson(animeStatistics)
    }

    @TypeConverter
    fun toAnimeStatistics(json: String): AnimeStatistics {
        return jsonAdapter.fromJson(json) ?: throw IllegalArgumentException("Invalid JSON string")
    }
}