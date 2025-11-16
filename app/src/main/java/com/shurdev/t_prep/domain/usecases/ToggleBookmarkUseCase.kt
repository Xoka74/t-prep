package com.shurdev.t_prep.domain.usecases;

import com.shurdev.t_prep.domain.repositories.CardRepository;

class ToggleBookmarkUseCase(
        private val cardRepository:CardRepository
) {
    suspend operator fun invoke(questionId: String) {
        cardRepository.toggleBookmark(questionId)
    }
}

