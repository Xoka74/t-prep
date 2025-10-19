package com.shurdev.t_prep.di

import android.content.Context
import androidx.room.Room
import com.shurdev.t_prep.data.local.AppDatabase
import com.shurdev.t_prep.data.local.dao.SubjectDao
import com.shurdev.t_prep.data.repositories.QuestionRepositoryImpl
import com.shurdev.t_prep.data.repositories.StudySessionRepositoryImpl
import com.shurdev.t_prep.data.repositories.SubjectRepositoryImpl
import com.shurdev.t_prep.domain.repositories.QuestionRepository
import com.shurdev.t_prep.domain.repositories.StudySessionRepository
import com.shurdev.t_prep.domain.repositories.SubjectRepository
import com.shurdev.t_prep.domain.usecases.GetSubjectsUseCase
import com.shurdev.t_prep.domain.usecases.SaveSessionUseCase
import com.shurdev.t_prep.domain.usecases.StartQuizUseCase
import com.shurdev.t_prep.domain.usecases.ToggleBookmarkUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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
    fun provideSubjectDao(
        db: AppDatabase,
    ) : SubjectDao{
        return db.subjectDao()
    }


    @Provides
    @Singleton
    fun provideGetSubjectsUseCase(repository: SubjectRepository): GetSubjectsUseCase {
        return GetSubjectsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideStartQuizUseCase(repository: QuestionRepository): StartQuizUseCase {
        return StartQuizUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideToggleBookmarkUseCase(repository: QuestionRepository): ToggleBookmarkUseCase {
        return ToggleBookmarkUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSaveSessionUseCase(repository: StudySessionRepository): SaveSessionUseCase {
        return SaveSessionUseCase(repository)
    }

    // Repositories
    @Provides
    @Singleton
    fun provideSubjectRepository(subjectDao: SubjectDao): SubjectRepository {
        return SubjectRepositoryImpl(subjectDao)
    }

    @Provides
    @Singleton
    fun provideQuestionRepository(): QuestionRepository {
        return QuestionRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideStudySessionRepository(): StudySessionRepository {
        return StudySessionRepositoryImpl()
    }
}