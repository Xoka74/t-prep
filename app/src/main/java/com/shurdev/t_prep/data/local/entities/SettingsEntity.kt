package com.shurdev.t_prep.data.local.entities

import com.shurdev.t_prep.domain.models.ThemeType
import kotlinx.serialization.Serializable

@Serializable
data class SettingsEntity(
    val themeType: ThemeType = ThemeType.System,
)