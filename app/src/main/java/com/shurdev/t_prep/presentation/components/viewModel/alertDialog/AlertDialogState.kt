package com.shurdev.t_prep.presentation.components.viewModel.alertDialog

sealed interface AlertDialogState

data object AlertDialogIdleState : AlertDialogState

data object AlertDialogLoadingState : AlertDialogState

data object AlertDialogDoneState : AlertDialogState

data object AlertDialogErrorState : AlertDialogState