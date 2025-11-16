package com.shurdev.t_prep.di.modules

import com.shurdev.t_prep.data.local.AppDatabase
import com.shurdev.t_prep.data.local.dao.ModuleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DaoModule {
    @Provides
    fun provideModuleDao(
        db: AppDatabase,
    ): ModuleDao {
        return db.moduleDao()
    }
}