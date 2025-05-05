package me.fernandesleite.mahoulist.feature.anime.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.fernandesleite.mahoulist.core.extension.NetworkExtensions.onErrorDo
import me.fernandesleite.mahoulist.feature.anime.data.model.remote.animeranking.AnimeRankingType
import me.fernandesleite.mahoulist.feature.anime.domain.GetPagedAnimeRankingUseCase
import me.fernandesleite.mahoulist.feature.auth.utils.AuthConstants
import me.fernandesleite.mahoulist.feature.user.domain.GetUserUseCase
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val getPagedAnimeRankingUseCase: GetPagedAnimeRankingUseCase,
    private val getUserUseCase: GetUserUseCase
): ViewModel() {
    private var currentPage = 1


    fun getTest() {
        viewModelScope.launch {
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