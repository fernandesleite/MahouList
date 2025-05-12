package me.fernandesleite.mahoulist.feature.anime.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import me.fernandesleite.mahoulist.core.extension.NetworkExtensions.onErrorDo
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.animeranking.AnimeRankingType
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.animeranking.RankingData
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.common.Anime
import me.fernandesleite.mahoulist.feature.anime.domain.GetPagedAnimeRankingUseCase
import me.fernandesleite.mahoulist.feature.anime.domain.GetPagedSuggestedUseCase
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val getPagedAnimeRankingUseCase: GetPagedAnimeRankingUseCase,
    private val getPagedSuggestedUseCase: GetPagedSuggestedUseCase,
): ViewModel() {
    private var currentPage = 1

    private val _popular = MutableStateFlow<List<RankingData>>(emptyList())
    val popularList: StateFlow<List<RankingData>> = _popular

    private val _currentlyAiring = MutableStateFlow<List<RankingData>>(emptyList())
    val currentlyAiringList: StateFlow<List<RankingData>> = _currentlyAiring

    private val _suggested = MutableStateFlow<List<Anime>>(emptyList())
    val suggestedList: StateFlow<List<Anime>> = _suggested

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText

    fun sendText(text: String) {
        _searchText.value = text
    }

    fun getTest() {
        viewModelScope.launch {
            val flow1 = getPagedAnimeRankingUseCase.invoke(AnimeRankingType.BY_POPULARITY, currentPage)
            val flow2 = getPagedAnimeRankingUseCase.invoke(AnimeRankingType.AIRING, currentPage)
            val flow3 = getPagedSuggestedUseCase.invoke(currentPage)

            combine(flow1, flow2, flow3) { popular, currentlyAiring, suggested ->
                Triple(popular, currentlyAiring, suggested)
            }.collect { response ->
                val popular = response.first
                val currentlyAiring = response.second
                val suggested = response.third

                popular.onErrorDo {
                    Log.d("TAG", "combine: $it")
                }
                currentlyAiring.onErrorDo {
                    Log.d("TAG", "combine: $it")
                }
                suggested.onErrorDo {
                    Log.d("TAG", "combine: $it")
                }

                popular.data?.let { data ->
                    _popular.value = data
                }
                currentlyAiring.data?.let { data ->
                    _currentlyAiring.value = data
                }
                suggested.data?.let { data ->
                    _suggested.value = data
                }
            }



            getPagedAnimeRankingUseCase.invoke(AnimeRankingType.ALL, currentPage).collect { response ->
                response.onErrorDo {

                }
                response.data?.let { data ->
                    currentPage++
                }
            }
        }
    }
}