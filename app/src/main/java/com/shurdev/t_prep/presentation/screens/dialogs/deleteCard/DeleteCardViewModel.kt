package com.shurdev.t_prep.presentation.screens.dialogs.deleteCard

import androidx.lifecycle.SavedStateHandle
import com.shurdev.t_prep.domain.repositories.CardRepository
import com.shurdev.t_prep.presentation.components.viewModel.alertDialog.AlertDialogViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DeleteCardViewModel @Inject constructor(
    private val cardRepository: CardRepository,
    savedStateHandle: SavedStateHandle,
) : AlertDialogViewModel() {

    private val moduleId = savedStateHandle["moduleId"] ?: ""

    private val cardId = savedStateHandle["cardId"] ?: ""

    override suspend fun confirmAction() {
        cardRepository.deleteCard(moduleId.toInt(), cardId.toInt())
    }
}