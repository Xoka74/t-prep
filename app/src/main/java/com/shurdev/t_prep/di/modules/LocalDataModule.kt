package com.shurdev.t_prep.di.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.room.Room
import com.shurdev.t_prep.data.constants.LocalKeys
import com.shurdev.t_prep.data.dataSource.AuthDataSource
import com.shurdev.t_prep.data.dataSource.SettingsDataSource
import com.shurdev.t_prep.data.dataSource.settingsEntityDataStore
import com.shurdev.t_prep.data.local.AppDatabase
import com.shurdev.t_prep.data.local.entities.SettingsEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "exam_prep.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAuthDataSource(sharedPreferences: SharedPreferences): AuthDataSource {
        return AuthDataSource(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(LocalKeys.FILENAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSettingsDataSource(dataStore: DataStore<SettingsEntity>) : SettingsDataSource {
        return SettingsDataSource(dataStore)
    }

    @Provides
    @Singleton
    fun provideSettingsDataStore(@ApplicationContext context: Context): DataStore<SettingsEntity> {
        return context.settingsEntityDataStore
    }
}