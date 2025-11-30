package com.shurdev.t_prep.domain.repositories

import com.shurdev.t_prep.domain.models.Settings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val settings: Flow<Settings>

    suspend fun updateSettings(transform: suspend (Settings) -> Settings)
}