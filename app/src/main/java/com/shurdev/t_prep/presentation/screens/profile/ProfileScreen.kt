package com.shurdev.t_prep.presentation.screens.profile

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.t_prep.presentation.screens.profile.components.ProfileHeader
import com.shurdev.t_prep.presentation.screens.profile.components.ProfileMenu
import com.shurdev.t_prep.R
import com.shurdev.t_prep.data.managers.AccountManager
import com.shurdev.t_prep.presentation.components.layout.DefaultScreenLayout
import com.shurdev.t_prep.presentation.screens.profile.viewModel.ProfileErrorState
import com.shurdev.t_prep.presentation.screens.profile.viewModel.ProfileLoadedState
import com.shurdev.t_prep.presentation.screens.profile.viewModel.ProfileLoadingState
import com.shurdev.t_prep.presentation.screens.profile.viewModel.ProfileUiState
import com.shurdev.t_prep.presentation.screens.profile.viewModel.ProfileViewModel
import com.shurdev.t_prep.presentation.components.actions.SettingsAction
import com.shurdev.t_prep.presentation.components.buttons.PrimaryButton
import kotlinx.coroutines.launch

@Composable
internal fun ProfileRoute(
    onSettingsClick: () -> Unit = {},
) {

    val viewModel = hiltViewModel<ProfileViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val accountManager = remember {
        AccountManager(context as ComponentActivity)
    }

    fun onLogoutClick() {
        coroutineScope.launch {
            accountManager.logout()
            viewModel.logout()
        }
    }

    ProfileScreen(
        uiState = uiState,
        onLogoutClick = ::onLogoutClick,
        onSettingsClick = onSettingsClick,
        onTryAgain = viewModel::loadProfile,
    )
}

@Composable
internal fun ProfileScreen(
    uiState: ProfileUiState,
    onLogoutClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onTryAgain: () -> Unit = {},
) {
    DefaultScreenLayout(
        title = stringResource(R.string.profile),
        actions = {
            SettingsAction(
                onClick = onSettingsClick,
            )
        }
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                when (uiState) {
                    ProfileLoadingState -> CircularProgressIndicator()
                    is ProfileLoadedState -> ProfileHeader(user = uiState.user)
                    ProfileErrorState -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Spacer(Modifier.height(8.dp))

                            PrimaryButton(
                                text = stringResource(R.string.try_again),
                                onClick = onTryAgain,
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            ProfileMenu(
                onLogoutClick = onLogoutClick,
            )
        }
    }
}