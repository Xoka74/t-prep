package com.shurdev.t_prep.domain.usecases;

import com.shurdev.t_prep.domain.models.Card;
import com.shurdev.t_prep.domain.repositories.CardRepository;
import com.shurdev.t_prep.domain.repositories.IntervalRepetitionsRepository

class StartQuizUseCase(
    private val cardRepository: CardRepository,
    private val intervalRepetitionsRepository: IntervalRepetitionsRepository
) {
    suspend operator fun invoke(moduleId: String): List<Card> {
//        return cardRepository.getCardByModule(moduleId)
        return intervalRepetitionsRepository.getCardsForRepetition(moduleId)
    }
}
