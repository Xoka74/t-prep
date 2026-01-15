package com.shurdev.t_prep.presentation.screens.dialogs.editCard.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.shurdev.t_prep.domain.forms.FormSubmissionErrorState
import com.shurdev.t_prep.domain.forms.FormSubmittedState
import com.shurdev.t_prep.domain.forms.FormSubmittingState
import com.shurdev.t_prep.data.models.CardData
import com.shurdev.t_prep.domain.forms.FormEditingState
import com.shurdev.t_prep.domain.forms.FormPreparationFailedState
import com.shurdev.t_prep.domain.forms.FormPreparationState
import com.shurdev.t_prep.domain.models.AccessLevel
import com.shurdev.t_prep.domain.repositories.CardRepository
import com.shurdev.t_prep.presentation.components.viewModel.form.FormViewModel
import com.shurdev.t_prep.presentation.screens.dialogs.editCard.viewModel.form.EditCardForm
import com.shurdev.t_prep.presentation.screens.dialogs.editCard.viewModel.form.EditCardValidationError
import com.shurdev.t_prep.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditCardViewModel @Inject constructor(
    private val cardRepository: CardRepository,
    savedStateHandle: SavedStateHandle,
) : FormViewModel<EditCardValidationError, EditCardForm>(
    initialData = EditCardForm(
        CardData("", ""),
        cardData = CardData("", ""),
    )
) {
    private val moduleId = savedStateHandle["moduleId"] ?: ""

    private val cardId = savedStateHandle["cardId"] ?: ""

    init {
        prepareForm()
    }

    private fun prepareForm() {
        updateUiState { FormPreparationState }

        viewModelScope.launch {
            runSuspendCatching {
                val card = cardRepository.getCardById(moduleId.toInt(), cardId.toInt())

                if (card == null) {
                    updateUiState { FormPreparationFailedState }
                    return@launch
                }

                val data = CardData(card.question, card.answer)

                updateFormData {
                    EditCardForm(
                        initialCardData = data,
                        cardData = data,
                    )
                }

                updateUiState { FormEditingState }
            }.onFailure {
                updateUiState { FormPreparationFailedState }
            }
        }
    }

    override fun sendForm() {
        updateUiState { FormSubmittingState }
        viewModelScope.launch {
            runSuspendCatching {
                if (formData.cardData != formData.initialCardData) {
                    cardRepository.editCard(moduleId.toInt(), cardId.toInt(), formData.cardData)
                }
                updateUiState { FormSubmittedState(Unit) }
            }.onFailure {
                updateUiState { FormSubmissionErrorState }
            }
        }
    }

    fun onQuestionChange(value: String) {
        updateFormData { form -> form.copy(cardData = form.cardData.copy(question = value)) }
    }

    fun onAnswerChange(value: String) {
        updateFormData { form -> form.copy(cardData = form.cardData.copy(answer = value)) }
    }
}