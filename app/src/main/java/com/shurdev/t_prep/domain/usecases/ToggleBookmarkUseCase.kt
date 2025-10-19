package com.shurdev.t_prep.domain.usecases;

import com.shurdev.t_prep.domain.repositories.QuestionRepository;

class ToggleBookmarkUseCase(
        private val questionRepository:QuestionRepository
) {
    suspend operator fun invoke(questionId: String) {
        questionRepository.toggleBookmark(questionId)
    }
}

