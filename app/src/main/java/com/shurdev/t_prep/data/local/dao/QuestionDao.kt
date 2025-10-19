package com.shurdev.t_prep.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.shurdev.t_prep.data.local.entities.QuestionEntity

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions WHERE subjectId = :subjectId")
    suspend fun getQuestionsBySubject(subjectId: String): List<QuestionEntity>

    @Query("UPDATE questions SET isBookmarked = NOT isBookmarked WHERE id = :questionId")
    suspend fun toggleBookmark(questionId: String)

    @Query("SELECT * FROM questions WHERE isBookmarked = 1")
    suspend fun getBookmarkedQuestions(): List<QuestionEntity>
}