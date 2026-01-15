package com.shurdev.t_prep.presentation.screens.createModule.viewModel

import androidx.lifecycle.viewModelScope
import com.darkrockstudios.libraries.mpfilepicker.MPFile
import com.shurdev.t_prep.domain.forms.FormSubmissionErrorState
import com.shurdev.t_prep.domain.forms.FormSubmittedState
import com.shurdev.t_prep.data.models.CardData
import com.shurdev.t_prep.data.models.CardDataDto
import com.shurdev.t_prep.domain.models.AccessLevel
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
class   CreateModuleViewModel @Inject constructor(
    private val moduleRepository: ModuleRepository,
    private val cardRepository: CardRepository,
) : FormViewModel<ModuleFormValidationError, ModuleForm>(
    initialData = ModuleForm()
) {
    override fun sendForm() {
        viewModelScope.launch {
            runSuspendCatching {
                val module = moduleRepository.createModule(formData.module)

                for (card in formData.cards) {
                    cardRepository.createCard(
                        CardDataDto(
                            card.question,
                            card.answer,
                            module.id.toInt()
                        )
                    )
                }

                updateUiState { FormSubmittedState }
            }.onFailure {
                updateUiState { FormSubmissionErrorState }
            }
        }
    }

    fun importCards(file: MPFile<Any>) {
        viewModelScope.launch {
            runSuspendCatching {
                val importedCards = cardRepository.importCards(file)
                updateFormData { data ->
                    data.copy(cards = data.cards + importedCards)
                }
            }.onFailure {

            }
        }
    }

    fun onModuleNameChanged(value: String) {
        updateFormData { form -> form.copy(module = form.module.copy(name = value)) }
    }


    fun onModuleDescriptionChanged(value: String) {
        updateFormData { form -> form.copy(module = form.module.copy(description = value)) }
    }

    fun onAddCard() {
        updateFormData { form ->
            form.copy(cards = form.cards + CardData("", ""))
        }
    }

    fun onCardChange(index: Int, card: CardData) {
        updateFormData { form ->
            form.copy(cards = form.cards.mapIndexed { cardIndex, item ->
                if (cardIndex == index) card else item
            })
        }
    }

    fun onCardRemove(index: Int) {
        updateFormData { form ->
            form.copy(cards = form.cards.filterIndexed { ind, _ -> ind != index })
        }
    }

    fun onViewAccessChange(value: AccessLevel) {
        updateFormData { form -> form.copy(module = form.module.copy(viewAccess = value)) }
    }

    fun onEditAccessChange(value: AccessLevel) {
        updateFormData { form -> form.copy(module = form.module.copy(editAccess = value)) }
    }
}