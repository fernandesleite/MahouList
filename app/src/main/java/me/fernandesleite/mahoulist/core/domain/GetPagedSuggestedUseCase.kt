package me.fernandesleite.mahoulist.core.domain

import kotlinx.coroutines.flow.Flow
import me.fernandesleite.mahoulist.core.data.model.remote.common.Anime
import me.fernandesleite.mahoulist.core.util.AnimeConstants
import me.fernandesleite.mahoulist.core.util.Response
import javax.inject.Inject

class GetPagedSuggestedUseCase @Inject constructor(private val repository: AnimeRepository) {
    suspend operator fun invoke(
        page: Int,
        pageSize: Int = AnimeConstants.ANIME_SUGGESTED_PAGE_SIZE,
    ): Flow<Response<List<Anime>>> {
        return repository.getSuggestedAnime(
            pageSize = pageSize,
            page = page
        )
    }
}