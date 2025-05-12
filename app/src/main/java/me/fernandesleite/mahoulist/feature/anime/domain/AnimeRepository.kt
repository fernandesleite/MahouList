package me.fernandesleite.mahoulist.feature.anime.domain

import kotlinx.coroutines.flow.Flow
import me.fernandesleite.mahoulist.core.util.Response
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.animedetails.AnimeDetailsModel
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.animeranking.AnimeRankingType
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.animeranking.Ranking
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.animeranking.RankingData
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.common.Anime
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.common.AnimeWrapper

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