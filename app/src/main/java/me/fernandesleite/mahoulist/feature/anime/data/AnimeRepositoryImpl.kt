package me.fernandesleite.mahoulist.feature.anime.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.fernandesleite.mahoulist.core.extension.NetworkExtensions.getResponse
import me.fernandesleite.mahoulist.core.util.Response
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.animedetails.AnimeDetailsModel
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.animeranking.AnimeRankingType
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.animeranking.RankingData
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.common.Anime
import me.fernandesleite.mahoulist.feature.anime.domain.AnimeRepository
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeApi: AnimeService

) : AnimeRepository {
    override suspend fun getAnimeList(
        query: String?,
        limit: Int?,
        offset: Int?,
        fields: String?
    ): Flow<Response<List<Anime>>> {
        return flow {
            emit(animeApi.getAnimeList(query, limit, offset, fields)
                .animeList
                .map { it.anime })
        }.getResponse()
    }


    override suspend fun getAnimeDetails(id: Int): Flow<Response<AnimeDetailsModel>> {
        return flow {
            emit(animeApi.getAnimeDetail(id))
        }.getResponse()
    }

    override suspend fun getAnimeRanking(
        rankingType: AnimeRankingType,
        pageSize: Int,
        page: Int
    ): Flow<Response<List<RankingData>>> {
        val offset = (page - 1) * pageSize
        return flow {
            emit(
                animeApi
                    .getAnimeRanking(
                        rankingType.type,
                        pageSize,
                        offset
                    )
                    .data
            )
        }.getResponse()
    }

    override suspend fun getSeasonalAnime(
        year: Int,
        season: String,
        sort: String,
        offset: Int,
        limit: Int
    ): Flow<Response<List<Anime>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSuggestedAnime(
        pageSize: Int,
        page: Int
    ): Flow<Response<List<Anime>>> {
        val offset = (page - 1) * pageSize
        return flow {
            emit(animeApi.getSuggestedAnime(pageSize, offset).data.map { it.anime })
        }.getResponse()
    }
}