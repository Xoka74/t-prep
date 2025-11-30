package com.shurdev.data.mappers

import com.shurdev.t_prep.data.local.entities.SettingsEntity
import com.shurdev.t_prep.domain.models.Settings

fun SettingsEntity.toDomainModel(): Settings {
    return Settings(
        themeType = themeType,
    )
}

fun Settings.toEntity(): SettingsEntity {
    return SettingsEntity(
        themeType = themeType,
    )
}