package com.shurdev.t_prep.presentation.screens.createModule.viewModel

import androidx.lifecycle.viewModelScope
import com.shurdev.domain.forms.FormSubmissionErrorState
import com.shurdev.domain.forms.FormSubmittedState
import com.shurdev.t_prep.data.models.ModuleData
import com.shurdev.t_prep.domain.eventPublishers.module.ModuleEventPublisher
import com.shurdev.t_prep.domain.repositories.ModuleRepository
import com.shurdev.t_prep.presentation.screens.modules.viewModel.form.ModuleFormValidationError
import com.shurdev.t_prep.presentation.components.viewModel.form.FormViewModel
import com.shurdev.t_prep.presentation.screens.modules.viewModel.form.ModuleForm
import com.shurdev.t_prep.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateModuleViewModel @Inject constructor(
    private val moduleRepository: ModuleRepository,
) : FormViewModel<ModuleFormValidationError, ModuleForm>(
    initialData = ModuleForm()
) {
    override fun sendForm() {
        viewModelScope.launch {
            runSuspendCatching {
                val data = ModuleData(formData.name, formData.description)
                moduleRepository.createModule(data)

                updateUiState { FormSubmittedState(Unit) }
            }.onFailure {
                updateUiState { FormSubmissionErrorState }
            }
        }
    }
}