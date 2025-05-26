package me.fernandesleite.mahoulist.core.domain

import kotlinx.coroutines.flow.Flow
import me.fernandesleite.mahoulist.core.data.model.remote.animedetails.AnimeDetailsModel
import me.fernandesleite.mahoulist.core.data.model.remote.animeranking.AnimeRankingType
import me.fernandesleite.mahoulist.core.data.model.remote.animeranking.RankingData
import me.fernandesleite.mahoulist.core.data.model.remote.common.Anime
import me.fernandesleite.mahoulist.core.util.Response

interface AnimeRepository {

    suspend fun getAnimeList(
        query: String? = null,
        limit: Int? = null,
        offset: Int? = null,
        fields: String? = null
    ): Flow<Response<List<Anime>>>

    suspend fun getAnimeDetails(id: Int): Flow<Response<AnimeDetailsModel>>

    suspend fun getAnimeRanking(
        rankingType: AnimeRankingType,
        pageSize: Int,
        page: Int
    ): Flow<Response<List<RankingData>>>

    suspend fun getSeasonalAnime(
        year: Int,
        season: String,
        sort: String,
        offset: Int,
        limit: Int
    ): Flow<Response<List<Anime>>>

    suspend fun getSuggestedAnime(
        pageSize: Int,
        page: Int
    ): Flow<Response<List<Anime>>>
}