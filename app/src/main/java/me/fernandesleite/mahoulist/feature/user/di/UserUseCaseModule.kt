package me.fernandesleite.mahoulist.feature.user.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.fernandesleite.mahoulist.feature.user.domain.GetUserUseCase
import me.fernandesleite.mahoulist.feature.user.domain.UserRepository

@Module
@InstallIn(SingletonComponent::class)
class UserUseCaseModule {

    @Provides
    fun provideGetUserUseCase(userRepository: UserRepository): GetUserUseCase {
        return GetUserUseCase(userRepository)
    }
}