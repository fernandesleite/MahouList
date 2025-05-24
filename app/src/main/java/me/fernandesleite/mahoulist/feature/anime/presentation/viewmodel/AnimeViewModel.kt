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
import me.fernandesleite.mahoulist.core.ui.UiState
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.animeranking.AnimeRankingType
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.common.Anime
import me.fernandesleite.mahoulist.feature.anime.domain.GetPagedAnimeListUseCase
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val getPagedAnimeListUseCase: GetPagedAnimeListUseCase,
) : ViewModel() {
    private var currentPage = 1

    private val _popular = MutableStateFlow<List<Anime>>(emptyList())
    val popularList: StateFlow<List<Anime>> = _popular

    private val _currentlyAiring = MutableStateFlow<List<Anime>>(emptyList())
    val currentlyAiringList: StateFlow<List<Anime>> = _currentlyAiring

    private val _suggested = MutableStateFlow<List<Anime>>(emptyList())
    val suggestedList: StateFlow<List<Anime>> = _suggested


    private val _uiState = MutableStateFlow(UiState.LOADING)
    val state: StateFlow<UiState> = _uiState


    fun getHomeContent() {
        viewModelScope.launch {
            val flow1 =
                getPagedAnimeListUseCase.invoke(AnimeRankingType.BY_POPULARITY, currentPage)
            val flow2 = getPagedAnimeListUseCase.invoke(AnimeRankingType.AIRING, currentPage)
            val flow3 = getPagedAnimeListUseCase.invoke(AnimeRankingType.SUGGESTED, currentPage)

            combine(flow1, flow2, flow3) { popular, currentlyAiring, suggested ->
                Triple(popular, currentlyAiring, suggested)
            }.collect { response ->
                val popular = response.first
                val currentlyAiring = response.second
                val suggested = response.third

                popular.onErrorDo {
                    Log.d("TAG", "combine: $it")
                    _uiState.value = UiState.ERROR
                }
                currentlyAiring.onErrorDo {
                    Log.d("TAG", "combine: $it")
                    _uiState.value = UiState.ERROR
                }
                suggested.onErrorDo {
                    Log.d("TAG", "combine: $it")
                    _uiState.value = UiState.ERROR
                }

                _popular.value = popular.data ?: emptyList()
                _currentlyAiring.value = currentlyAiring.data ?: emptyList()
                _suggested.value = suggested.data ?: emptyList()

                _uiState.value = UiState.CONTENT
            }
        }
    }
}