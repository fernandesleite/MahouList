package me.fernandesleite.mahoulist.feature.auth.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.fernandesleite.mahoulist.feature.auth.data.repository.OAuthRefreshRepositoryImpl
import me.fernandesleite.mahoulist.feature.auth.data.repository.OAuthRepositoryImpl
import me.fernandesleite.mahoulist.feature.auth.domain.repository.OAuthRefreshRepository
import me.fernandesleite.mahoulist.feature.auth.domain.repository.OAuthRepository

@Module(includes = [OAuthUseCaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class OAuthModule {

    @Binds
    abstract fun bindOAuthRepository(oAuthRepositoryImpl: OAuthRepositoryImpl): OAuthRepository

    @Binds
    abstract fun bindRefreshRepository(oAuthRefreshRepositoryImpl: OAuthRefreshRepositoryImpl): OAuthRefreshRepository

}