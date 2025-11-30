package com.shurdev.t_prep.presentation.screens.modules

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.t_prep.R
import com.shurdev.t_prep.presentation.components.ErrorView
import com.shurdev.t_prep.presentation.components.layout.DefaultScreenLayout
import com.shurdev.t_prep.presentation.components.textFields.SearchField
import com.shurdev.t_prep.presentation.screens.modules.components.ModuleCard
import com.shurdev.t_prep.presentation.screens.modules.viewModel.ModulesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModulesScreen(
    viewModel: ModulesViewModel = hiltViewModel(),
    onModuleClick: (String) -> Unit,
    onCreateModuleClick: () -> Unit,
) {
    val state = viewModel.uiState.value

    DefaultScreenLayout(
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
    ) {
        when {
            state.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            state.error != null -> {
                ErrorView(error = state.error, onRetry = { viewModel.loadModules() })
            }

            else -> {
                Column {
                    SearchField(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        debounceTimeMillis = 300L,
                        onSearchTextChange = {},
                        hint = stringResource(R.string.search_hint),
                    )

                    LazyColumn {
                        items(state.modules) { module ->
                            ModuleCard(
                                module = module,
                                onClick = { onModuleClick(module.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}