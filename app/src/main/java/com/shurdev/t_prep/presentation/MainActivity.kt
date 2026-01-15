package com.shurdev.t_prep.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.util.Consumer
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shurdev.t_prep.domain.models.ThemeType
import com.shurdev.t_prep.presentation.screens.settings.viewModel.SettingsLoadedState
import com.shurdev.t_prep.presentation.screens.settings.viewModel.SettingsViewModel
import com.shurdev.t_prep.ui.theme.TPrepTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val settingsViewModel = hiltViewModel<SettingsViewModel>()

            val settingsUiState by settingsViewModel.uiState.collectAsState()

            val themeType = (settingsUiState as? SettingsLoadedState)?.settings?.themeType

            val navController = rememberNavController()

            LaunchedEffect(Unit) {
                handleIntent(intent, navController)
            }

            val isDarkTheme = when (themeType) {
                ThemeType.Light -> false
                ThemeType.Dark -> true
                else -> isSystemInDarkTheme()
            }

            TPrepTheme(
                darkTheme = false
            ) {
                TPrepApp(
                    navController = navController
                )
            }

            DisposableEffect(Unit) {
                val listener = Consumer<Intent> { newIntent ->
                    handleIntent(newIntent, navController)
                }
                addOnNewIntentListener(listener)
                onDispose { removeOnNewIntentListener(listener) }
            }
        }
    }

    private fun handleIntent(intent: Intent, navController: NavHostController) {
        val moduleId = intent.extras?.getString("moduleId") ?: return

        navController.navigate("quiz/$moduleId")
    }
}