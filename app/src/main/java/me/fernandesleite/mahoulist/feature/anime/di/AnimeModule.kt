package me.fernandesleite.mahoulist.feature.anime.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.fernandesleite.mahoulist.feature.anime.data.AnimeRepositoryImpl
import me.fernandesleite.mahoulist.feature.anime.domain.AnimeRepository
import me.fernandesleite.mahoulist.feature.anime.domain.GetPagedAnimeRankingUseCase
import me.fernandesleite.mahoulist.feature.auth.domain.repository.OAuthRefreshRepository
import me.fernandesleite.mahoulist.feature.auth.domain.usecase.GetRefreshTokenUseCase

@Module(includes = [AnimeUseCaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class AnimeModule {

    @Binds
    abstract fun bindAnimeRepository(animeRepositoryImpl: AnimeRepositoryImpl): AnimeRepository

}