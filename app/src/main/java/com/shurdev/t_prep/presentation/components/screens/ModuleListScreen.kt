package com.shurdev.t_prep.presentation.components.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shurdev.t_prep.presentation.components.ErrorView
import com.shurdev.t_prep.presentation.components.layout.DefaultScreenLayout
import com.shurdev.t_prep.presentation.components.loaders.Loader
import com.shurdev.t_prep.presentation.components.textFields.SearchField
import com.shurdev.t_prep.presentation.screens.modules.ModulesState
import com.shurdev.t_prep.presentation.screens.modules.components.ModuleCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModuleListScreen(
    state: ModulesState,
    onModuleClick: (String) -> Unit,
    onSearchChange: (String) -> Unit,
    onLoadModules: (String, Boolean) -> Unit,
    title: String,
    fab: (@Composable () -> Unit)? = null,
    showViewAccess: Boolean = true,
) {
    var search by remember { mutableStateOf("") }

    DefaultScreenLayout(
        title = title,
        fab = fab,
    ) {
        Column {
            SearchField(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                debounceTimeMillis = 300,
                hint = "Поиск модуля",
                searchText = search,
                onSearchTextChange = {
                    search = it
                    onSearchChange(search)
                },
            )
            PullToRefreshBox(
                onRefresh = {
                    onLoadModules(search, true)
                },
                isRefreshing = state.isPullToRefresh
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    when {
                        state.isLoading -> Loader()
                        state.error != null -> {
                            ErrorView(
                                error = state.error,
                                onRetry = {
                                    onLoadModules(search, false)
                                }
                            )
                        }
                        !state.isLoading && !state.isPullToRefresh -> {
                            if (state.modules.isEmpty()) {
                                Column(
                                    Modifier
                                        .fillMaxSize()
                                        .verticalScroll(rememberScrollState()),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        "Ничего не найдено",
                                        textAlign = TextAlign.Center
                                    )
                                }
                            } else {
                                LazyColumn {
                                    items(state.modules) { module ->
                                        ModuleCard(
                                            module = module,
                                            showViewAccess = showViewAccess,
                                            onClick = { onModuleClick(module.id) }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}