package com.shurdev.t_prep.di.modules

import com.shurdev.t_prep.domain.repositories.CardRepository
import com.shurdev.t_prep.domain.repositories.StudySessionRepository
import com.shurdev.t_prep.domain.usecases.SaveSessionUseCase
import com.shurdev.t_prep.domain.usecases.StartQuizUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideStartQuizUseCase(repository: CardRepository): StartQuizUseCase {
        return StartQuizUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSaveSessionUseCase(repository: StudySessionRepository): SaveSessionUseCase {
        return SaveSessionUseCase(repository)
    }
}