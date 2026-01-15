package com.shurdev.t_prep.presentation.screens.modules

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.t_prep.R
import com.shurdev.t_prep.presentation.components.layout.RequestNotificationPermissionLauncher
import com.shurdev.t_prep.presentation.components.screens.ModuleListScreen
import com.shurdev.t_prep.presentation.screens.modules.viewModel.ModulesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModulesScreen(
    viewModel: ModulesViewModel = hiltViewModel(),
    onModuleClick: (String) -> Unit,
    onCreateModuleClick: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()

    RequestNotificationPermissionLauncher()

    ModuleListScreen(
        state = state,
        onModuleClick = onModuleClick,
        onSearchChange = viewModel::onSearchChange,
        onLoadModules = viewModel::loadModules,
        title = stringResource(R.string.library),
        fab = {
            FloatingActionButton(
                modifier = Modifier.padding(10.dp),
                onClick = onCreateModuleClick,
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.create_module)
                )
            }
        }
    )
}