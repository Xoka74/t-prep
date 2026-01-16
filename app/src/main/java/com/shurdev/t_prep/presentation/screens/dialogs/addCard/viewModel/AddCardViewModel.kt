package com.shurdev.t_prep.presentation.screens.dialogs.addCard.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.shurdev.t_prep.data.models.CardDataDto
import com.shurdev.t_prep.domain.forms.FormSubmissionErrorState
import com.shurdev.t_prep.domain.forms.FormSubmittedState
import com.shurdev.t_prep.domain.forms.FormSubmittingState
import com.shurdev.t_prep.domain.repositories.CardRepository
import com.shurdev.t_prep.presentation.components.viewModel.form.FormViewModel
import com.shurdev.t_prep.presentation.screens.dialogs.editCard.viewModel.form.EditCardForm
import com.shurdev.t_prep.presentation.screens.dialogs.editCard.viewModel.form.EditCardValidationError
import com.shurdev.t_prep.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCardViewModel @Inject constructor(
    private val cardRepository: CardRepository,
    savedStateHandle: SavedStateHandle,
) : FormViewModel<EditCardValidationError, EditCardForm>(
    initialData = EditCardForm()
) {
    private val moduleId = savedStateHandle["moduleId"] ?: ""

    override fun sendForm() {
        updateUiState { FormSubmittingState }
        viewModelScope.launch {
            runSuspendCatching {
                cardRepository.createCard(
                    CardDataDto(
                        formData.cardData.question,
                        formData.cardData.answer,
                        moduleId.toInt(),
                    )
                )

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