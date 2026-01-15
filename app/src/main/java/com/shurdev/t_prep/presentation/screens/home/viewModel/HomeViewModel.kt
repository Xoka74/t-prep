package com.shurdev.t_prep.presentation.screens.home.viewModel

import androidx.lifecycle.viewModelScope
import com.shurdev.t_prep.domain.eventPublishers.module.ModuleEventPublisher
import com.shurdev.t_prep.domain.repositories.ModuleRepository
import com.shurdev.t_prep.presentation.components.viewModel.BaseViewModel
import com.shurdev.t_prep.presentation.screens.modules.ModulesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moduleRepository: ModuleRepository,
    private val moduleEventPublisher: ModuleEventPublisher,
)  : BaseViewModel<ModulesState>(
    initialState = ModulesState()
) {
    init {
        loadModules()
        subscribeToEvents()
    }

    fun subscribeToEvents() {
        viewModelScope.launch {
            moduleEventPublisher.events.collect {
                loadModules()
            }
        }
    }

    fun onSearchChange(text: String) {
        loadModules(text)
    }

    fun loadModules(search: String = "", pullToRefresh: Boolean = false) {
        viewModelScope.launch {
            updateUiState { it.copy(isLoading = !pullToRefresh, isPullToRefresh = pullToRefresh) }
            try {
                val modules = moduleRepository.getAllModules(search)
                updateUiState {
                    it.copy(
                        modules = modules,
                        isLoading = false,
                        isPullToRefresh = false,
                    )
                }
            } catch (e: Exception) {
                updateUiState {
                    it.copy(
                        error = e.message,
                        isLoading = false,
                        isPullToRefresh = false,
                    )
                }
            }
        }
    }
}