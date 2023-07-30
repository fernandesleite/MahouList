package me.fernandesleite.mahoulist.core.di.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    companion object {
        private const val PREFERENCES_MAHOULIST = "preferences_mahoulist"
    }

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCES_MAHOULIST, Context.MODE_PRIVATE)
    }
}