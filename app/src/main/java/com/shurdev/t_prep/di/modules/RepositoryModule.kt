package com.shurdev.t_prep.di.modules

import android.app.Application
import com.github.doyaaaaaken.kotlincsv.client.CsvReader
import com.shurdev.t_prep.data.api.AuthApi
import com.shurdev.t_prep.data.api.CardsApi
import com.shurdev.t_prep.data.api.IntervalRepetitionsApi
import com.shurdev.t_prep.data.api.MeApi
import com.shurdev.t_prep.data.api.ModulesApi
import com.shurdev.t_prep.data.api.PushApi
import com.shurdev.t_prep.data.dataSource.AuthDataSource
import com.shurdev.t_prep.data.dataSource.SettingsDataSource
import com.shurdev.t_prep.data.local.dao.ModuleDao
import com.shurdev.t_prep.data.repositories.AuthRepositoryImpl
import com.shurdev.t_prep.data.repositories.CardRepositoryImpl
import com.shurdev.t_prep.data.repositories.IntervalRepetitionsRepositoryImpl
import com.shurdev.t_prep.data.repositories.LogoutRepositoryImpl
import com.shurdev.t_prep.data.repositories.MeRepository
import com.shurdev.t_prep.data.repositories.MeRepositoryImpl
import com.shurdev.t_prep.data.repositories.ModuleRepositoryImpl
import com.shurdev.t_prep.data.repositories.SettingsRepositoryImpl
import com.shurdev.t_prep.data.repositories.StudySessionRepositoryImpl
import com.shurdev.t_prep.domain.eventPublishers.module.ModuleEventPublisher
import com.shurdev.t_prep.domain.repositories.AuthRepository
import com.shurdev.t_prep.domain.repositories.CardRepository
import com.shurdev.t_prep.domain.repositories.IntervalRepetitionsRepository
import com.shurdev.t_prep.domain.repositories.LogoutRepository
import com.shurdev.t_prep.domain.repositories.ModuleRepository
import com.shurdev.t_prep.domain.repositories.SettingsRepository
import com.shurdev.t_prep.domain.repositories.StudySessionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideModuleRepository(
        moduleDao: ModuleDao,
        modulesApi: ModulesApi,
        moduleEventPublisher: ModuleEventPublisher,
    ): ModuleRepository {
        return ModuleRepositoryImpl(moduleDao, modulesApi, moduleEventPublisher)
    }

    @Provides
    @Singleton
    fun provideCardRepository(
        cardsApi: CardsApi,
        application: Application,
        csvReader: CsvReader,
    ): CardRepository {
        return CardRepositoryImpl(cardsApi, application, csvReader)
    }

    @Provides
    @Singleton
    fun provideStudySessionRepository(): StudySessionRepository {
        return StudySessionRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        authDataSource: AuthDataSource,
        authApi: AuthApi,
        pushApi: PushApi,
    ): AuthRepository {
        return AuthRepositoryImpl(
            authDataSource = authDataSource,
            authApi = authApi,
            pushApi = pushApi,
        )
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(
        settingsDataSource: SettingsDataSource,
    ): SettingsRepository {
        return SettingsRepositoryImpl(
            settingsDataSource = settingsDataSource,
        )
    }

    @Provides
    @Singleton
    fun provideMeRepository(meApi: MeApi): MeRepository {
        return MeRepositoryImpl(meApi)
    }

    @Provides
    @Singleton
    fun provideLogoutRepository(
        authDataSource: AuthDataSource,
    ): LogoutRepository {
        return LogoutRepositoryImpl(
            authDataSource = authDataSource,
        )
    }

    @Provides
    @Singleton
    fun provideIntervalRepetitionsRepository(intervalRepetitionsApi: IntervalRepetitionsApi): IntervalRepetitionsRepository {
        return IntervalRepetitionsRepositoryImpl(intervalRepetitionsApi)
    }
}