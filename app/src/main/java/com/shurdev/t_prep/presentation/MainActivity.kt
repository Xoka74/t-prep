package com.shurdev.t_prep.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.t_prep.domain.models.ThemeType
import com.shurdev.t_prep.presentation.screens.settings.viewModel.SettingsLoadedState
import com.shurdev.t_prep.presentation.screens.settings.viewModel.SettingsViewModel
import com.shurdev.t_prep.ui.theme.TPrepTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val settingsViewModel = hiltViewModel<SettingsViewModel>()

            val settingsUiState by settingsViewModel.uiState.collectAsState()

            val themeType = (settingsUiState as? SettingsLoadedState)?.settings?.themeType

            val isDarkTheme = when (themeType) {
                ThemeType.Light -> false
                ThemeType.Dark -> true
                else -> isSystemInDarkTheme()
            }

            TPrepTheme(
                darkTheme = isDarkTheme
            ) {
                TPrepApp()
            }
        }
    }
}