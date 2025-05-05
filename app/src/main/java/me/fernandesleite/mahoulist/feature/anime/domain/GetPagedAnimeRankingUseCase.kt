package me.fernandesleite.mahoulist.feature.anime.domain

import kotlinx.coroutines.flow.Flow
import me.fernandesleite.mahoulist.core.util.Response
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.animeranking.AnimeRankingType
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.animeranking.RankingData
import me.fernandesleite.mahoulist.feature.anime.utils.AnimeConstants

class GetPagedAnimeRankingUseCase(private val animeRepository: AnimeRepository) {
    suspend operator fun invoke(
        rankingType: AnimeRankingType,
        page: Int,
        pageSize: Int = AnimeConstants.ANIME_RANKING_PAGE_SIZE
    ): Flow<Response<List<RankingData>>> {
        return animeRepository.getAnimeRanking(
            rankingType = rankingType,
            pageSize = pageSize,
            page = page
        )
    }
}