package com.shurdev.t_prep.di.modules

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.shurdev.t_prep.data.api.AuthApi
import com.shurdev.t_prep.data.api.CardsApi
import com.shurdev.t_prep.data.api.ModulesApi
import com.shurdev.t_prep.data.dataSource.AuthDataSource
import com.shurdev.t_prep.data.helpers.GoogleAccountConsumer
import com.shurdev.t_prep.data.local.dao.ModuleDao
import com.shurdev.t_prep.data.repositories.AuthRepositoryImpl
import com.shurdev.t_prep.data.repositories.CardRepositoryImpl
import com.shurdev.t_prep.data.repositories.ModuleRepositoryImpl
import com.shurdev.t_prep.data.repositories.StudySessionRepositoryImpl
import com.shurdev.t_prep.di.qualifiers.ServerClientId
import com.shurdev.t_prep.domain.repositories.AuthRepository
import com.shurdev.t_prep.domain.repositories.CardRepository
import com.shurdev.t_prep.domain.repositories.ModuleRepository
import com.shurdev.t_prep.domain.repositories.StudySessionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    // Repositories
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
        googleSignInClient: GoogleSignInClient,
        @ApplicationContext context: Context,
        @ServerClientId serverClientId: String,
        googleAccountConsumer: GoogleAccountConsumer,
    ): AuthRepository {
        return AuthRepositoryImpl(
            authApi = authApi,
            context = context,
            googleAccountConsumer = googleAccountConsumer,
            googleSignInClient = googleSignInClient,
            authDataSource = authDataSource,
            serverClientId = serverClientId
        )
    }

}