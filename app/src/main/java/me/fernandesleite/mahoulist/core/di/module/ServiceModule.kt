package me.fernandesleite.mahoulist.core.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.fernandesleite.mahoulist.core.di.qualifier.NoTokenRefresh
import me.fernandesleite.mahoulist.core.di.qualifier.TokenRefresh
import me.fernandesleite.mahoulist.feature.auth.data.OAuthService
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    // Services
    @NoTokenRefresh
    @Provides
    fun provideOAuthServiceAuth(@NoTokenRefresh retrofit: Retrofit): OAuthService {
        return retrofit.create(OAuthService::class.java)
    }

    @TokenRefresh
    @Provides
    fun provideOAuthServiceRefresh(@TokenRefresh retrofit: Retrofit): OAuthService {
        return retrofit.create(OAuthService::class.java)
    }
}



