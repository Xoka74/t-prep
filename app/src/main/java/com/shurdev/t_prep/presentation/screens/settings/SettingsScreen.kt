package com.shurdev.t_prep.presentation.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.t_prep.domain.models.ThemeType
import com.shurdev.t_prep.R
import com.shurdev.t_prep.domain.models.Settings
import com.shurdev.t_prep.presentation.components.ErrorView
import com.shurdev.t_prep.presentation.components.buttons.SingleChoiceDialogButton
import com.shurdev.t_prep.presentation.screens.settings.viewModel.SettingsErrorState
import com.shurdev.t_prep.presentation.screens.settings.viewModel.SettingsLoadedState
import com.shurdev.t_prep.presentation.screens.settings.viewModel.SettingsLoadingState
import com.shurdev.t_prep.presentation.screens.settings.viewModel.SettingsUiState
import com.shurdev.t_prep.presentation.screens.settings.viewModel.SettingsViewModel
import com.shurdev.t_prep.presentation.components.layout.DefaultScreenLayout
import com.shurdev.t_prep.presentation.components.loaders.Loader
import com.shurdev.t_prep.presentation.utils.toResString

@Composable
internal fun SettingsRoute(
    onDismiss: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel<SettingsViewModel>(),
) {

    val uiState by viewModel.uiState.collectAsState()

    SettingsScreen(
        onBackInvoked = onDismiss,
        uiState = uiState,
        onThemeSelected = viewModel::updateTheme,
    )
}


@Composable
internal fun SettingsScreen(
    uiState: SettingsUiState,
    onThemeSelected: (ThemeType) -> Unit,
    onBackInvoked: () -> Unit,
) {
    DefaultScreenLayout(
        title = stringResource(R.string.settings),
        onBackInvoked = onBackInvoked,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            when (uiState) {
                SettingsErrorState -> ErrorView(
                    error = uiState.toString(),
                )
                SettingsLoadingState -> Loader()
                is SettingsLoadedState -> SettingsScreenContent(
                    settings = uiState.settings,
                    onThemeSelected = onThemeSelected,
                )
            }
        }
    }
}

@Composable
internal fun SettingsScreenContent(
    settings: Settings,
    onThemeSelected: (ThemeType) -> Unit,
) {
    Column {
        SingleChoiceDialogButton(
            modifier = Modifier.padding(16.dp),
            title = stringResource(R.string.app_theme),
            items = ThemeType.entries,
            onItemSelected = onThemeSelected,
            itemToString = { it.toResString() },
            selectedItem = settings.themeType,
        )
    }
}