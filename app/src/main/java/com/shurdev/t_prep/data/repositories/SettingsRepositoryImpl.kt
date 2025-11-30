package com.shurdev.t_prep.data.repositories

import com.shurdev.t_prep.data.dataSource.SettingsDataSource
import com.shurdev.data.mappers.toDomainModel
import com.shurdev.data.mappers.toEntity
import com.shurdev.t_prep.domain.models.Settings
import com.shurdev.t_prep.domain.repositories.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val settingsDataSource: SettingsDataSource,
) : SettingsRepository {
    override val settings: Flow<Settings> = settingsDataSource.settings

    override suspend fun updateSettings(transform: suspend (Settings) -> Settings) {
        return settingsDataSource.updateSettings {
            transform(it.toDomainModel()).toEntity()
        }
    }
}