package com.shurdev.t_prep.domain.repositories

import com.shurdev.t_prep.domain.models.Question


interface QuestionRepository {
    suspend fun getQuestionsBySubject(subjectId: String): List<Question>
    suspend fun getQuestionById(id: String): Question?
    suspend fun toggleBookmark(questionId: String)
    suspend fun getBookmarkedQuestions(): List<Question>
}
