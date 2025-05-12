package me.fernandesleite.mahoulist.feature.anime.domain

import kotlinx.coroutines.flow.Flow
import me.fernandesleite.mahoulist.core.util.Response
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.common.Anime
import me.fernandesleite.mahoulist.feature.anime.utils.AnimeConstants

class GetPagedSuggestedUseCase(private val repository: AnimeRepository) {
    suspend operator fun invoke(
        page: Int,
        pageSize: Int = AnimeConstants.ANIME_SUGGESTED_PAGE_SIZE
    ): Flow<Response<List<Anime>>> {
        return repository.getSuggestedAnime(
            pageSize = pageSize,
            page = page
        )
    }
}