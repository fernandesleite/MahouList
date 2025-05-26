package me.fernandesleite.mahoulist.core.data.model.remote.animedetails


import com.squareup.moshi.Json
import me.fernandesleite.mahoulist.core.data.model.remote.common.MainPicture

data class AnimeDetailsModel(
    @Json(name = "alternative_titles")
    val alternativeTitles: AlternativeTitles,
    @Json(name = "average_episode_duration")
    val averageEpisodeDuration: Int,
    @Json(name = "background")
    val background: String,
    @Json(name = "broadcast")
    val broadcast: Broadcast,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "end_date")
    val endDate: String,
    @Json(name = "genres")
    val genres: List<Genre>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "main_picture")
    val mainPicture: MainPicture,
    @Json(name = "mean")
    val mean: Double,
    @Json(name = "media_type")
    val mediaType: String,
    @Json(name = "my_list_status")
    val myListStatus: MyListStatus,
    @Json(name = "nsfw")
    val nsfw: String,
    @Json(name = "num_episodes")
    val numEpisodes: Int,
    @Json(name = "num_list_users")
    val numListUsers: Int,
    @Json(name = "num_scoring_users")
    val numScoringUsers: Int,
    @Json(name = "pictures")
    val pictures: List<Picture>,
    @Json(name = "popularity")
    val popularity: Int,
    @Json(name = "rank")
    val rank: Int,
    @Json(name = "rating")
    val rating: String,
    @Json(name = "recommendations")
    val recommendations: List<Recommendation>,
    @Json(name = "related_anime")
    val relatedAnime: List<RelatedAnime>,
    @Json(name = "related_manga")
    val relatedManga: List<Any>,
    @Json(name = "source")
    val source: String,
    @Json(name = "start_date")
    val startDate: String,
    @Json(name = "start_season")
    val startSeason: StartSeason,
    @Json(name = "statistics")
    val statistics: Statistics,
    @Json(name = "status")
    val status: String,
    @Json(name = "studios")
    val studios: List<Studio>,
    @Json(name = "synopsis")
    val synopsis: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "updated_at")
    val updatedAt: String
)