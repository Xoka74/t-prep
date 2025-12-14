package com.shurdev.t_prep.presentation.screens.cards.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shurdev.t_prep.domain.repositories.CardRepository
import com.shurdev.t_prep.presentation.components.cards.CardFace
import com.shurdev.t_prep.presentation.screens.cards.CardsState
import com.shurdev.t_prep.presentation.screens.cards.SlideDirection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    private val cardRepository: CardRepository
) : ViewModel() {

    private val _uiState = mutableStateOf(CardsState())
    val uiState: State<CardsState> = _uiState

    fun loadCards(moduleId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val cards = cardRepository.getCardByModule(moduleId)
                _uiState.value = _uiState.value.copy(
                    cards = cards,
                    currentIndex = 0,
                    cardFace = CardFace.Front,
                    isLoading = false
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

    fun onQuizClicked(){

    }

    fun onTestClicked(){

    }
}