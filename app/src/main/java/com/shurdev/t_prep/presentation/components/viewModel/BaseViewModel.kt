package com.shurdev.t_prep.presentation.components.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<TState>(
    initialState: TState,
) : ViewModel() {
    private val _uiState = MutableStateFlow(initialState)

    val uiState = _uiState.asStateFlow()

    protected fun updateUiState(function: (TState) -> TState) = _uiState.update(function)

    protected inline fun <reified TStateFrom : TState, TStateTo : TState> transformUiState(
        crossinline function: (TStateFrom) -> TStateTo,
    ) {
        val value = uiState.value

        if (value is TStateFrom) {
            updateUiState { function(value) }
        }
    }
}