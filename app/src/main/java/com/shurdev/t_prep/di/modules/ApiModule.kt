package com.shurdev.t_prep.di.modules

import com.shurdev.t_prep.data.api.AuthApi
import com.shurdev.t_prep.data.api.CardsApi
import com.shurdev.t_prep.data.api.IntervalRepetitionsApi
import com.shurdev.t_prep.data.api.MeApi
import com.shurdev.t_prep.data.api.ModulesApi
import com.shurdev.t_prep.data.api.PushApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {


    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit) = retrofit.create<AuthApi>()

    @Provides
    @Singleton
    fun provideCardsApi(retrofit: Retrofit) = retrofit.create<CardsApi>()

    @Provides
    @Singleton
    fun provideModulesApi(retrofit: Retrofit) = retrofit.create<ModulesApi>()

    @Provides
    @Singleton
    fun providePushApi(retrofit: Retrofit) = retrofit.create<PushApi>()

    @Provides
    @Singleton
    fun provideMeApi(retrofit: Retrofit) = retrofit.create<MeApi>()

    @Provides
    @Singleton
    fun provideIntervalRepetitionsApi(retrofit: Retrofit) = retrofit.create<IntervalRepetitionsApi>()
}