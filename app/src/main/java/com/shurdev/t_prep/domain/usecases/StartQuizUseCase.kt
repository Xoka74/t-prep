package com.shurdev.t_prep.domain.usecases;

import com.shurdev.t_prep.domain.models.Card;
import com.shurdev.t_prep.domain.repositories.CardRepository;

class StartQuizUseCase(
    private val cardRepository: CardRepository
) {
    suspend operator fun invoke(subjectId: String): List<Card> {
        return cardRepository.getCardByModule(subjectId)
    }
}
