package com.shurdev.t_prep.presentation.screens.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.t_prep.R
import com.shurdev.t_prep.presentation.components.screens.ModuleListScreen
import com.shurdev.t_prep.presentation.screens.home.viewModel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onModuleClick: (String) -> Unit,
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val state by viewModel.uiState.collectAsState()

    ModuleListScreen(
        state = state,
        onModuleClick = onModuleClick,
        onSearchChange = viewModel::onSearchChange,
        onLoadModules = viewModel::loadModules,
        showViewAccess = false,
        title = stringResource(R.string.public_modules),
    )
}