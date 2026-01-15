package com.shurdev.t_prep.presentation.screens.dialogs.deleteModuleDialog

import androidx.lifecycle.SavedStateHandle
import com.shurdev.t_prep.domain.repositories.ModuleRepository
import com.shurdev.t_prep.presentation.components.viewModel.alertDialog.AlertDialogViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DeleteModuleViewModel @Inject constructor(
    private val moduleRepository: ModuleRepository,
    savedStateHandle: SavedStateHandle,
) : AlertDialogViewModel() {

    private val moduleId = savedStateHandle["moduleId"] ?: ""

    override suspend fun confirmAction() {
        moduleRepository.deleteModule(moduleId.toInt())
    }
}