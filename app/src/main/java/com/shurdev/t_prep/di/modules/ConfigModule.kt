package com.shurdev.t_prep.di.modules

import com.shurdev.t_prep.BuildConfig
import com.shurdev.t_prep.di.qualifiers.BaseUrl
import com.shurdev.t_prep.di.qualifiers.ServerClientId
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConfigModule {
    @Provides
    @Singleton
    @ServerClientId
    fun provideServerClientId(): String = BuildConfig.serverClientId

    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl(): String = BuildConfig.baseUrl
}