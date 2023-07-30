package me.fernandesleite.mahoulist.feature.auth.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.fernandesleite.mahoulist.feature.auth.domain.repository.OAuthRefreshRepository
import me.fernandesleite.mahoulist.feature.auth.domain.repository.OAuthRepository
import me.fernandesleite.mahoulist.feature.auth.domain.usecase.GetRefreshTokenUseCase
import me.fernandesleite.mahoulist.feature.auth.domain.usecase.SaveAccessTokenUseCase
import me.fernandesleite.mahoulist.feature.auth.domain.usecase.SaveRefreshTokenUseCase

@Module
@InstallIn(SingletonComponent::class)
class OAuthUseCaseModule {

    @Provides
    fun provideAccessUseCase(repository: OAuthRepository): SaveAccessTokenUseCase =
        SaveAccessTokenUseCase(repository)

    @Provides
    fun provideRefreshUseCase(repository: OAuthRefreshRepository): SaveRefreshTokenUseCase =
        SaveRefreshTokenUseCase(repository)

    @Provides
    fun provideGetRefreshTokenUseCase(repository: OAuthRefreshRepository): GetRefreshTokenUseCase =
        GetRefreshTokenUseCase(repository)

}