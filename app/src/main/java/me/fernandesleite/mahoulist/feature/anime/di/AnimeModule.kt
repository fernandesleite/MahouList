package me.fernandesleite.mahoulist.feature.anime.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.fernandesleite.mahoulist.feature.anime.data.AnimeRepositoryImpl
import me.fernandesleite.mahoulist.feature.anime.domain.AnimeRepository

@Module(includes = [AnimeUseCaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class AnimeModule {

    @Binds
    abstract fun bindAnimeRepository(animeRepositoryImpl: AnimeRepositoryImpl): AnimeRepository

}