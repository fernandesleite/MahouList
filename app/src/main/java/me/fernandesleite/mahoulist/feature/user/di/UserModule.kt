package me.fernandesleite.mahoulist.feature.user.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.fernandesleite.mahoulist.feature.user.data.UserRepositoryImpl
import me.fernandesleite.mahoulist.feature.user.domain.UserRepository

@Module(includes = [UserUseCaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class UserModule {

    @Binds
    abstract fun bindsUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}