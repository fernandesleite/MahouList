package me.fernandesleite.mahoulist.feature.anime.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.fernandesleite.mahoulist.core.util.Response
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.animeranking.AnimeRankingType
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.common.Anime
import me.fernandesleite.mahoulist.feature.anime.utils.AnimeConstants

class GetPagedAnimeListUseCase(private val animeRepository: AnimeRepository) {
    suspend operator fun invoke(
        rankingType: AnimeRankingType,
        page: Int,
        pageSize: Int = AnimeConstants.ANIME_RANKING_PAGE_SIZE,
    ): Flow<Response<List<Anime>>> {
        if (AnimeRankingType.SUGGESTED == rankingType) {
            return animeRepository.getSuggestedAnime(
                pageSize = pageSize,
                page = page
            )
        }
        return animeRepository.getAnimeRanking(
            rankingType = rankingType,
            pageSize = pageSize,
            page = page
        ).map { response ->
            when (response) {
                is Response.Success -> {
                    Response.Success(response.data?.sortedBy { it.ranking.rank }?.map {
                        it.anime
                    })
                }

                is Response.Error -> Response.Error(response.message ?: "Generic Error")
                is Response.NetworkError -> Response.NetworkError(
                    response.codeError,
                    response.message ?: "Generic Network Error"
                )

                is Response.Loading -> Response.Loading()
            }
        }
    }
}
