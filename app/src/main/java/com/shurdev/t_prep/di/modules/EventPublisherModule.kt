package com.shurdev.t_prep.di.modules

import com.shurdev.t_prep.data.publishers.ModuleEventPublisherImpl
import com.shurdev.t_prep.domain.eventPublishers.module.ModuleEventPublisher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EventPublisherModule {

    @Provides
    @Singleton
    fun provideModuleEventPublisher() : ModuleEventPublisher {
        return ModuleEventPublisherImpl()
    }
}