package me.fernandesleite.mahoulist.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.fernandesleite.mahoulist.core.data.AnimeRepositoryImpl
import me.fernandesleite.mahoulist.core.domain.AnimeRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class AnimeModule {

    @Binds
    abstract fun bindAnimeRepository(animeRepositoryImpl: AnimeRepositoryImpl): AnimeRepository

}