package me.fernandesleite.mahoulist.feature.anime.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.fernandesleite.mahoulist.feature.anime.domain.AnimeRepository
import me.fernandesleite.mahoulist.feature.anime.domain.GetPagedAnimeRankingUseCase

@Module
@InstallIn(SingletonComponent::class)
class AnimeUseCaseModule {
    @Provides
    fun provideGetPagedAnimeRankingUseCase(repository: AnimeRepository): GetPagedAnimeRankingUseCase =
        GetPagedAnimeRankingUseCase(repository)
}