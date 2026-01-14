package com.shurdev.t_prep.presentation.screens.cards.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shurdev.t_prep.domain.repositories.CardRepository
import com.shurdev.t_prep.domain.repositories.IntervalRepetitionsRepository
import com.shurdev.t_prep.domain.repositories.ModuleRepository
import com.shurdev.t_prep.presentation.components.cards.CardFace
import com.shurdev.t_prep.presentation.screens.cards.CardsState
import com.shurdev.t_prep.presentation.screens.cards.SlideDirection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    private val cardRepository: CardRepository,
    private val moduleRepository: ModuleRepository,
    private val intervalRepetitionsRepository: IntervalRepetitionsRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val moduleId = savedStateHandle["moduleId"] ?: ""

    private val _uiState = mutableStateOf(CardsState())
    val uiState: State<CardsState> = _uiState

    init {
        loadCards()
    }

    fun loadCards() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val cards = cardRepository.getCardByModule(moduleId)
                val moduleName = moduleRepository.getModuleById(moduleId)?.name
                val isIntervalRepetitionsEnabled = true // TODO

                _uiState.value = _uiState.value.copy(
                    moduleName = moduleName,
                    cards = cards,
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

    fun onIntervalRepetitionsToggle(isEnabled: Boolean){
        viewModelScope.launch {
            intervalRepetitionsRepository.setIsIntervalRepetitionsEnabled(moduleId, isEnabled)

            val isIntervalRepetitionsEnabled = intervalRepetitionsRepository.getIsIntervalRepetitionsEnabled(moduleId)
            _uiState.value = _uiState.value.copy(
                isIntervalRepetitionsEnabled = isIntervalRepetitionsEnabled
            )
        }
    }
}