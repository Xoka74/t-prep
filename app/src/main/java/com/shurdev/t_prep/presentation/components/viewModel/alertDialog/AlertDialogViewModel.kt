package com.shurdev.t_prep.presentation.components.viewModel.alertDialog

import androidx.lifecycle.viewModelScope
import com.shurdev.t_prep.presentation.components.viewModel.BaseViewModel
import com.shurdev.t_prep.utils.runSuspendCatching
import kotlinx.coroutines.launch

abstract class AlertDialogViewModel : BaseViewModel<AlertDialogState>(
    initialState = AlertDialogIdleState
) {
    protected abstract suspend fun confirmAction()

    fun onConfirm() {
        updateUiState { AlertDialogLoadingState }
        viewModelScope.launch {
            runSuspendCatching {
                confirmAction()
                updateUiState { AlertDialogDoneState }
            }.onFailure { it ->
                print(it)
                updateUiState { AlertDialogErrorState }
            }
        }
    }
}