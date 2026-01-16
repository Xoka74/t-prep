package com.shurdev.t_prep.presentation.screens.cards

import com.shurdev.t_prep.presentation.components.cards.CardFace
import com.shurdev.t_prep.domain.models.Card

data class CardsState(
    val moduleName: String? = null,
    val cards: List<Card> = emptyList(),
    val currentIndex: Int = 0,
    val cardFace: CardFace = CardFace.Front,
    val isLoading: Boolean = false,
    val error: String? = null,
    val slideDirection: SlideDirection = SlideDirection.Forward,
    val isDeleted: Boolean = false,
) {
    val currentCard: Card?
        get() = cards.getOrNull(currentIndex)
}

enum class SlideDirection {
    Forward, Backward
}
