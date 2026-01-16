package com.shurdev.t_prep.presentation.screens.createModule.viewModel

import androidx.lifecycle.viewModelScope
import com.shurdev.t_prep.domain.forms.FormSubmissionErrorState
import com.shurdev.t_prep.domain.forms.FormSubmittedState
import com.shurdev.t_prep.domain.models.AccessLevel
import com.shurdev.t_prep.domain.repositories.ModuleRepository
import com.shurdev.t_prep.presentation.components.viewModel.form.FormViewModel
import com.shurdev.t_prep.presentation.screens.modules.viewModel.form.ModuleForm
import com.shurdev.t_prep.presentation.screens.modules.viewModel.form.ModuleFormValidationError
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
                val module = moduleRepository.createModule(formData.module)

                updateUiState { FormSubmittedState(module.id) }
            }.onFailure {
                updateUiState { FormSubmissionErrorState }
            }
        }
    }

    fun onModuleNameChanged(value: String) {
        updateFormData { form -> form.copy(module = form.module.copy(name = value)) }
    }

    fun onModuleDescriptionChanged(value: String) {
        updateFormData { form -> form.copy(module = form.module.copy(description = value)) }
    }

    fun onViewAccessChange(value: AccessLevel) {
        updateFormData { form -> form.copy(module = form.module.copy(viewAccess = value)) }
    }

    fun onEditAccessChange(value: AccessLevel) {
        updateFormData { form -> form.copy(module = form.module.copy(editAccess = value)) }
    }
}