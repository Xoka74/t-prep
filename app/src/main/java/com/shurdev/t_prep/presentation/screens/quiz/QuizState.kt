package com.shurdev.t_prep.presentation.screens.quiz

import com.shurdev.t_prep.domain.models.Card

data class QuizState(
    val cards: List<Card> = emptyList(),
    val currentCardIndex: Int = 0,
    val selectedAnswer: Int? = null,
    val isAnswerCorrect: Boolean? = null,
    val score: Int = 0,
    val isLoading: Boolean = false,
    val showResults: Boolean = false,
    val error: String? = null
) {
    val currentCard: Card?
        get() = cards.getOrNull(currentCardIndex)
}