package com.shurdev.t_prep.data.repositories

import com.shurdev.t_prep.domain.models.Difficulty
import com.shurdev.t_prep.domain.models.Question
import com.shurdev.t_prep.domain.repositories.QuestionRepository

class QuestionRepositoryImpl : QuestionRepository {
    override suspend fun getQuestionsBySubject(subjectId: String): List<Question> {
        return (1..10)
            .map {
                Question(
                    id = "id_$it",
                    subjectId = "subjectId_$it",
                    question = "question_$it",
                    options = listOf("a", "b", "c"),
                    isBookmarked = false,
                    difficulty = Difficulty.MEDIUM,
                    correctAnswer = it,
                    explanation = "explanation_$it"
                )
            }
    }

    override suspend fun getQuestionById(id: String): Question? {
        return null
    }

    override suspend fun toggleBookmark(questionId: String) {
    }

    override suspend fun getBookmarkedQuestions(): List<Question> {
        return emptyList()
    }
}