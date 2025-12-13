package com.shurdev.t_prep.presentation.screens.createModule.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.shurdev.domain.forms.FormSubmissionErrorState
import com.shurdev.domain.forms.FormSubmittedState
import com.shurdev.t_prep.data.models.CardDataDto
import com.shurdev.t_prep.domain.repositories.CardRepository
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
    private val cardRepository: CardRepository
) : FormViewModel<ModuleFormValidationError, ModuleForm>(
    initialData = ModuleForm()
) {
    override fun sendForm() {
        viewModelScope.launch {
            runSuspendCatching {
                val module = moduleRepository.createModule(formData.module)

                for (card in formData.cards) {
                    val item = cardRepository.createCard(
                        CardDataDto(
                            card.question,
                            card.answer,
                            module.id.toInt()
                        )
                    )

                    Log.i("CreateModuleViewModel", item.toString())
                }

                updateUiState { FormSubmittedState(Unit) }
            }.onFailure {
                updateUiState { FormSubmissionErrorState }
            }
        }
    }
}