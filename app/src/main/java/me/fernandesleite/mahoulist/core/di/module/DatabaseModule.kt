package me.fernandesleite.mahoulist.core.di.module

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.fernandesleite.mahoulist.core.data.database.MahoulistDatabase
import me.fernandesleite.mahoulist.core.data.database.UserDao
import javax.inject.Singleton

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
    
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MahoulistDatabase {
        return Room.databaseBuilder(
            context,
            MahoulistDatabase::class.java,
            "mahoulist_database"
        ).build()
    }

    @Provides
    fun provideUserDao(database: MahoulistDatabase): UserDao {
        return database.userDao()
    }
}