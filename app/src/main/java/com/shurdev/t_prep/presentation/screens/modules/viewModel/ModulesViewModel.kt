package com.shurdev.t_prep.presentation.screens.modules.viewModel

import androidx.lifecycle.viewModelScope
import com.shurdev.t_prep.domain.eventPublishers.module.ModuleEventPublisher
import com.shurdev.t_prep.domain.usecases.GetModulesUseCase
import com.shurdev.t_prep.presentation.components.viewModel.BaseViewModel
import com.shurdev.t_prep.presentation.screens.modules.ModulesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModulesViewModel @Inject constructor(
    private val getModulesUseCase: GetModulesUseCase,
    private val moduleEventPublisher: ModuleEventPublisher,
) : BaseViewModel<ModulesState>(
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

    fun loadModules() {
        viewModelScope.launch {
            updateUiState { it.copy(isLoading = true) }
            try {
                val modules = getModulesUseCase()
                updateUiState {
                    it.copy(
                        modules = modules,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                updateUiState {
                    it.copy(
                        error = e.message,
                        isLoading = false
                    )
                }
            }
        }
    }
}