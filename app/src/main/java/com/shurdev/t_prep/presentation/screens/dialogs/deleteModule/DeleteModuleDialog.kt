package com.shurdev.t_prep.presentation.screens.dialogs.deleteModule

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.t_prep.presentation.components.dialogs.AlertDialogBaseScreen

@Composable
fun DeleteModuleDialog(
    onBack: () -> Unit,
) {
    val viewModel = hiltViewModel<DeleteModuleViewModel>()
    AlertDialogBaseScreen(
        viewModel = viewModel,
        title = "Удаление модуля",
        text = "Вы уверены, что хотите удалить выбранный модуль?",
        onDismissRequest = onBack,
    )
}