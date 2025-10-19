package com.shurdev.t_prep.domain.models

data class Question(
    val id: String,
    val subjectId: String,
    val question: String,
    val options: List<String>,
    val correctAnswer: Int,
    val explanation: String,
    val difficulty: Difficulty,
    val isBookmarked: Boolean = false
)