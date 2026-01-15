package com.shurdev.t_prep.presentation.screens.addCards.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.darkrockstudios.libraries.mpfilepicker.MPFile
import com.shurdev.t_prep.data.models.CardData
import com.shurdev.t_prep.data.models.CardDataDto
import com.shurdev.t_prep.domain.forms.FormSubmissionErrorState
import com.shurdev.t_prep.domain.forms.FormSubmittedState
import com.shurdev.t_prep.domain.repositories.CardRepository
import com.shurdev.t_prep.presentation.components.viewModel.form.FormViewModel
import com.shurdev.t_prep.presentation.screens.addCards.viewModel.form.CardsForm
import com.shurdev.t_prep.presentation.screens.addCards.viewModel.form.CardsFormValidationError
import com.shurdev.t_prep.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCardsViewModel @Inject constructor(
    private val cardRepository: CardRepository,
    savedStateHandle: SavedStateHandle,
) : FormViewModel<CardsFormValidationError, CardsForm>(
    initialData = CardsForm()
) {
    private val moduleId = savedStateHandle["moduleId"] ?: ""

    override fun sendForm() {
        viewModelScope.launch {
            runSuspendCatching {
                cardRepository.createCards(
                    moduleId = moduleId.toInt(),
                    data = formData.cards.map {
                        CardDataDto(
                            it.question,
                            it.answer,
                            moduleId.toInt()
                        )
                    }
                )

                updateUiState { FormSubmittedState(Unit) }
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
}