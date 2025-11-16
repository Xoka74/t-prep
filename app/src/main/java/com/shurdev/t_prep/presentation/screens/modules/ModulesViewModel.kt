package com.shurdev.t_prep.presentation.screens.modules;

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shurdev.t_prep.domain.usecases.GetModulesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModulesViewModel @Inject constructor(
    private val getModulesUseCase: GetModulesUseCase
) : ViewModel() {

    private val _uiState = mutableStateOf(ModulesState())
    val uiState: State<ModulesState> = _uiState

    init {
        loadModules()
    }

    fun loadModules() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val subjects = getModulesUseCase()
                _uiState.value = _uiState.value.copy(
                    modules = subjects,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }
}