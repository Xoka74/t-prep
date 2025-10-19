package com.shurdev.t_prep.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey
    val id: String,
    val subjectId: String,
    val question: String,
//    val options: List<String>,
    val correctAnswer: Int,
    val explanation: String,
//    val difficulty: Difficulty,
    val isBookmarked: Boolean = false
)