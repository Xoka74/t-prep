package com.shurdev.t_prep.presentation.screens.moduleSettings.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.shurdev.t_prep.domain.forms.FormEditingState
import com.shurdev.t_prep.domain.forms.FormPreparationFailedState
import com.shurdev.t_prep.domain.forms.FormPreparationState
import com.shurdev.t_prep.domain.forms.FormSubmissionErrorState
import com.shurdev.t_prep.domain.forms.FormSubmittedState
import com.shurdev.t_prep.data.models.ModuleData
import com.shurdev.t_prep.domain.models.AccessLevel
import com.shurdev.t_prep.domain.repositories.IntervalRepetitionsRepository
import com.shurdev.t_prep.domain.repositories.ModuleRepository
import com.shurdev.t_prep.presentation.components.viewModel.form.FormViewModel
import com.shurdev.t_prep.presentation.screens.moduleSettings.form.EditModuleForm
import com.shurdev.t_prep.presentation.screens.modules.viewModel.form.ModuleFormValidationError
import com.shurdev.t_prep.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModuleSettingsViewModel @Inject constructor(
    private val moduleRepository: ModuleRepository,
    private val intervalRepetitionsRepository: IntervalRepetitionsRepository,
    savedStateHandle: SavedStateHandle,
) : FormViewModel<ModuleFormValidationError, EditModuleForm>(
    initialData = EditModuleForm()
) {
    private val moduleId = savedStateHandle["moduleId"] ?: ""

    init {
        viewModelScope.launch {
            preloadModule()
        }
    }

    private suspend fun preloadModule() {
        updateUiState { FormPreparationState }

        runSuspendCatching {
            val module = moduleRepository.getModuleById(moduleId) ?: return

            val data = ModuleData(
                name = module.name,
                description = module.description,
                viewAccess = module.viewAccess,
                editAccess = module.editAccess,
                isIntervalRepetitionsEnabled = module.isIntervalRepetitionsEnabled
            )
            updateFormData {
                EditModuleForm(
                    initialData = data,
                    module = data,
                    totalCards = module.totalCards,
                )
            }

            updateUiState { FormEditingState }
        }.onFailure {
            updateUiState { FormPreparationFailedState }
        }
    }

    override fun sendForm() {
        viewModelScope.launch {
            runSuspendCatching {
                if (formData.module != formData.initialData) {
                    moduleRepository.editModule(moduleId.toInt(), formData.module)
                }

                updateUiState { FormSubmittedState(Unit) }
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

    fun onIntervalRepetitionsToggle(isEnabled: Boolean) {
        viewModelScope.launch {
            intervalRepetitionsRepository.setIsIntervalRepetitionsEnabled(moduleId, isEnabled)
            updateFormData { form -> form.copy(module = form.module.copy(isIntervalRepetitionsEnabled = isEnabled)) }
        }
    }
}