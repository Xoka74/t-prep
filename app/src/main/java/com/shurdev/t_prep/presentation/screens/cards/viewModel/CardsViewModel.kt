package com.shurdev.t_prep.presentation.screens.cards.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shurdev.t_prep.domain.eventPublishers.module.ModuleChangeEvent
import com.shurdev.t_prep.domain.eventPublishers.module.ModuleDeletedEvent
import com.shurdev.t_prep.domain.eventPublishers.module.ModuleEventPublisher
import com.shurdev.t_prep.domain.repositories.CardRepository
import com.shurdev.t_prep.domain.repositories.IntervalRepetitionsRepository
import com.shurdev.t_prep.domain.repositories.ModuleRepository
import com.shurdev.t_prep.presentation.components.cards.CardFace
import com.shurdev.t_prep.presentation.screens.cards.CardsState
import com.shurdev.t_prep.presentation.screens.cards.SlideDirection
import com.shurdev.t_prep.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    private val cardRepository: CardRepository,
    private val moduleRepository: ModuleRepository,
    private val intervalRepetitionsRepository: IntervalRepetitionsRepository,
    private val moduleEventPublisher: ModuleEventPublisher,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val moduleId = savedStateHandle["moduleId"] ?: ""

    private val _uiState = mutableStateOf(CardsState())
    val uiState: State<CardsState> = _uiState

    init {
        loadCards()
        subscribeToEvents()
    }

    fun subscribeToEvents() {
        viewModelScope.launch {
            moduleEventPublisher.events
                .mapNotNull { (it as? ModuleChangeEvent) }
                .filter { it.moduleId == moduleId }
                .collect {
                    if (it is ModuleDeletedEvent) {
                        _uiState.value = _uiState.value.copy(isDeleted = true)
                    } else {
                        loadCards()
                    }
                }
        }
    }

    fun loadCards() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                var intervalRepetitionCards =
                    intervalRepetitionsRepository.getCardsForRepetition(moduleId)

                if (intervalRepetitionCards.isEmpty()) {
                    intervalRepetitionCards = cardRepository.getCardByModule(moduleId)
                }
//                val cards = cardRepository.getCardByModule(moduleId)

                val module = moduleRepository.getModuleById(moduleId)
                val moduleName = module?.name
                val isIntervalRepetitionsEnabled = module?.isIntervalRepetitionsEnabled ?: false

                _uiState.value = _uiState.value.copy(
                    moduleName = moduleName,
                    cards = intervalRepetitionCards,
                    currentIndex = 0,
                    cardFace = CardFace.Front,
                    isLoading = false,
                    isIntervalRepetitionsEnabled = isIntervalRepetitionsEnabled
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }

    fun flipCard() {
        _uiState.value = _uiState.value.copy(
            cardFace = _uiState.value.cardFace.next
        )
    }

    fun nextCard() {
        val state = _uiState.value

        if (state.currentIndex + 1 >= state.cards.size) {
            return
        }

        _uiState.value = state.copy(
            currentIndex = state.currentIndex + 1,
            cardFace = CardFace.Front,
            slideDirection = SlideDirection.Forward
        )
    }

    fun prevCard() {
        val state = _uiState.value

        if (state.currentIndex - 1 < 0) {
            return
        }

        _uiState.value = state.copy(
            currentIndex = state.currentIndex - 1,
            cardFace = CardFace.Front,
            slideDirection = SlideDirection.Backward
        )
    }
}