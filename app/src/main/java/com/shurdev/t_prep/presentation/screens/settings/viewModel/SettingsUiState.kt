package com.shurdev.t_prep.presentation.screens.settings.viewModel

import com.shurdev.t_prep.domain.models.Settings

sealed interface SettingsUiState

data object SettingsLoadingState : SettingsUiState

data class SettingsLoadedState(val settings: Settings) : SettingsUiState

data object SettingsErrorState : SettingsUiState