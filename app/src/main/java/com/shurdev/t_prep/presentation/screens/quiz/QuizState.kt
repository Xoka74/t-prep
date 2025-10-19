package com.shurdev.t_prep.presentation.screens.quiz

import com.shurdev.t_prep.domain.models.Question

data class QuizState(
    val questions: List<Question> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val selectedAnswer: Int? = null,
    val isAnswerCorrect: Boolean? = null,
    val score: Int = 0,
    val isLoading: Boolean = false,
    val showResults: Boolean = false,
    val error: String? = null
) {
    val currentQuestion: Question?
        get() = questions.getOrNull(currentQuestionIndex)

    val progress: Float
        get() = if (questions.isEmpty()) 0f
        else (currentQuestionIndex + 1) / questions.size.toFloat()
}