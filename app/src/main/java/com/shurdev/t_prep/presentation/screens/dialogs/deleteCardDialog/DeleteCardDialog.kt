package com.shurdev.t_prep.presentation.screens.dialogs.deleteCardDialog

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.t_prep.presentation.components.dialogs.AlertDialogBaseScreen

@Composable
fun DeleteCardDialog(
    onBack: () -> Unit,
) {
    val viewModel = hiltViewModel<DeleteCardViewModel>()
    AlertDialogBaseScreen(
        viewModel = viewModel,
        title = "Удаление карточки",
        text = "Вы уверены, что хотите удалить выбранную карточку?",
        onDismissRequest = onBack,
    )
}