package me.fernandesleite.mahoulist.core.data

import me.fernandesleite.mahoulist.core.data.model.remote.anime.AnimeModel
import me.fernandesleite.mahoulist.core.data.model.remote.animedetails.AnimeDetailsModel
import me.fernandesleite.mahoulist.core.data.model.remote.animeranking.AnimeRankingModel
import me.fernandesleite.mahoulist.core.data.model.remote.seasonalanime.AnimeSortingType
import me.fernandesleite.mahoulist.core.data.model.remote.seasonalanime.SeasonalAnimeModel
import me.fernandesleite.mahoulist.core.data.model.remote.suggestedanime.SuggestedAnimeModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeService {
    @GET("anime")
    suspend fun getAnimeList(
        @Query("q") query: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("fields") fields: String? = null
    ): AnimeModel

    @GET("anime/{anime_id}")
    suspend fun getAnimeDetail(
        @Path("anime_id") id: Int,
        @Query("fields") fields: String? = null
    ): AnimeDetailsModel

    @GET("anime/ranking")
    suspend fun getAnimeRanking(
        @Query("ranking_type") rankingType: String,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("fields") fields: String? = null
    ): AnimeRankingModel

    @GET("season/{year}/{season}")
    suspend fun getSeasonalAnime(
        @Path("year") year: Int,
        @Path("season") season: String,
        @Query("sort") sort: AnimeSortingType? = null,
        @Query("offset") offset: Int? = null,
        @Query("limit") limit: Int? = null,
        @Query("fields") fields: String? = null
    ): SeasonalAnimeModel

    @GET("anime/suggestions")
    suspend fun getSuggestedAnime(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("fields") fields: String? = null
    ): SuggestedAnimeModel
}