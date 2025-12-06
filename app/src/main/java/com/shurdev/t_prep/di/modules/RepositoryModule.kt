package com.shurdev.t_prep.di.modules

import androidx.credentials.CredentialManager
import com.shurdev.t_prep.data.api.AuthApi
import com.shurdev.t_prep.data.api.CardsApi
import com.shurdev.t_prep.data.api.MeApi
import com.shurdev.t_prep.data.api.ModulesApi
import com.shurdev.t_prep.data.dataSource.AuthDataSource
import com.shurdev.t_prep.data.dataSource.SettingsDataSource
import com.shurdev.t_prep.data.local.dao.ModuleDao
import com.shurdev.t_prep.data.repositories.AuthRepositoryImpl
import com.shurdev.t_prep.data.repositories.CardRepositoryImpl
import com.shurdev.t_prep.data.repositories.LogoutRepositoryImpl
import com.shurdev.t_prep.data.repositories.MeRepository
import com.shurdev.t_prep.data.repositories.MeRepositoryImpl
import com.shurdev.t_prep.data.repositories.ModuleRepositoryImpl
import com.shurdev.t_prep.data.repositories.SettingsRepositoryImpl
import com.shurdev.t_prep.data.repositories.StudySessionRepositoryImpl
import com.shurdev.t_prep.domain.repositories.AuthRepository
import com.shurdev.t_prep.domain.repositories.CardRepository
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
    ): ModuleRepository {
        return ModuleRepositoryImpl(moduleDao, modulesApi)
    }

    @Provides
    @Singleton
    fun provideCardRepository(cardsApi: CardsApi): CardRepository {
        return CardRepositoryImpl(cardsApi)
    }

    @Provides
    @Singleton
    fun provideStudySessionRepository(): StudySessionRepository {
        return StudySessionRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        authApi: AuthApi,
        authDataSource: AuthDataSource,
    ): AuthRepository {
        return AuthRepositoryImpl(
            authApi = authApi,
            authDataSource = authDataSource,
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
}