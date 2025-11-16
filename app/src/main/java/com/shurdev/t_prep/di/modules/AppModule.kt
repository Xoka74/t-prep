package com.shurdev.t_prep.di.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.shurdev.t_prep.BuildConfig
import com.shurdev.t_prep.data.api.AuthApi
import com.shurdev.t_prep.data.api.CardsApi
import com.shurdev.t_prep.data.api.ModulesApi
import com.shurdev.t_prep.data.constants.LocalKeys
import com.shurdev.t_prep.data.dataSource.AuthDataSource
import com.shurdev.t_prep.data.helpers.GoogleAccountConsumer
import com.shurdev.t_prep.data.helpers.GoogleAuthCallbackHolder
import com.shurdev.t_prep.data.interceptors.TokenInterceptor
import com.shurdev.t_prep.data.local.AppDatabase
import com.shurdev.t_prep.data.local.dao.SubjectDao
import com.shurdev.t_prep.data.repositories.AuthRepositoryImpl
import com.shurdev.t_prep.data.repositories.CardRepositoryImpl
import com.shurdev.t_prep.data.repositories.ModuleRepositoryImpl
import com.shurdev.t_prep.data.repositories.StudySessionRepositoryImpl
import com.shurdev.t_prep.di.qualifiers.BaseUrl
import com.shurdev.t_prep.di.qualifiers.ServerClientId
import com.shurdev.t_prep.domain.repositories.AuthRepository
import com.shurdev.t_prep.domain.repositories.CardRepository
import com.shurdev.t_prep.domain.repositories.ModuleRepository
import com.shurdev.t_prep.domain.repositories.StudySessionRepository
import com.shurdev.t_prep.domain.usecases.GetModulesUseCase
import com.shurdev.t_prep.domain.usecases.SaveSessionUseCase
import com.shurdev.t_prep.domain.usecases.StartQuizUseCase
import com.shurdev.t_prep.domain.usecases.ToggleBookmarkUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
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
    ): SubjectDao {
        return db.subjectDao()
    }


    @Provides
    @Singleton
    fun provideGetSubjectsUseCase(repository: ModuleRepository): GetModulesUseCase {
        return GetModulesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideStartQuizUseCase(repository: CardRepository): StartQuizUseCase {
        return StartQuizUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideToggleBookmarkUseCase(repository: CardRepository): ToggleBookmarkUseCase {
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
    fun provideSubjectRepository(
        subjectDao: SubjectDao,
        modulesApi: ModulesApi,
    ): ModuleRepository {
        return ModuleRepositoryImpl(subjectDao, modulesApi)
    }

    @Provides
    @Singleton
    fun provideQuestionRepository(cardsApi: CardsApi): CardRepository {
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

    @Provides
    @Singleton
    fun provideGoogleAuthCallbackHolder(): GoogleAuthCallbackHolder {
        return GoogleAuthCallbackHolder()
    }


    @Provides
    @Singleton
    fun provideGoogleAccountConsumer(holder: GoogleAuthCallbackHolder): GoogleAccountConsumer {
        return GoogleAccountConsumer(holder)
    }

    @Provides
    @Singleton
    fun provideGoogleSignInClient(
        @ApplicationContext context: Context,
        @ServerClientId serverClientId: String,
    ): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestServerAuthCode(serverClientId)
            .requestEmail()
            .requestProfile()
            .build()

        return GoogleSignIn.getClient(context, gso)
    }

    @Provides
    @Singleton
    fun provideTokenInterceptor(authDataSource: AuthDataSource): TokenInterceptor {
        return TokenInterceptor(authDataSource)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        @BaseUrl baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
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
    @ServerClientId
    fun provideServerClientId(): String = BuildConfig.serverClientId

    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl(): String = BuildConfig.baseUrl

    @Provides
    @Singleton
    fun provideHttpClient(
        tokenInterceptor: TokenInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor)
            .build()
    }


    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit) = retrofit.create<AuthApi>()

    @Provides
    @Singleton
    fun provideCardsApi(retrofit: Retrofit) = retrofit.create<CardsApi>()

    @Provides
    @Singleton
    fun provideModulesApi(retrofit: Retrofit) = retrofit.create<ModulesApi>()
}