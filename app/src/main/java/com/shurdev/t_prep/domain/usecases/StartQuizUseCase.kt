package com.shurdev.t_prep.domain.usecases;

import com.shurdev.t_prep.domain.models.Question;
import com.shurdev.t_prep.domain.repositories.QuestionRepository;

class StartQuizUseCase(
    private val questionRepository: QuestionRepository
) {
    suspend operator fun invoke(subjectId: String): List<Question> {
        return questionRepository.getQuestionsBySubject(subjectId)
    }
}
