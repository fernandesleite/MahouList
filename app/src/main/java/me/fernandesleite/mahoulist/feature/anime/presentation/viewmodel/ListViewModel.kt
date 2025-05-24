package me.fernandesleite.mahoulist.feature.anime.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import me.fernandesleite.mahoulist.core.extension.NetworkExtensions.onErrorDo
import me.fernandesleite.mahoulist.core.ui.UiState
import me.fernandesleite.mahoulist.core.util.Response
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.animeranking.AnimeRankingType
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.common.Anime
import me.fernandesleite.mahoulist.feature.anime.domain.GetPagedAnimeListUseCase
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getPagedAnimeListUseCase: GetPagedAnimeListUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState.LOADING)
    val uiState: StateFlow<UiState> = _uiState

    private val _content = MutableStateFlow<List<Anime>>(emptyList())
    val content: StateFlow<List<Anime>> = _content

    private val requestMutex: Mutex = Mutex()
    private var currentPage = 1

    fun getListContent(
        source: AnimeRankingType,
    ) {
        viewModelScope.launch {
            requestMutex.withLock {
                getPagedAnimeListUseCase.invoke(source, currentPage)
                    .collect { response ->

                        response.onErrorDo {
                            Log.d("TAG", "combine: $it")
                            _uiState.value = UiState.ERROR
                        }

                        if (response is Response.Success) {
                            val list = content.value.toMutableList() + response.data!!
                            _content.value = list
                            _uiState.value = UiState.CONTENT


                        }

                        currentPage++

                    }

            }
        }
    }
}