package com.shurdev.t_prep.domain.models

data class StudySession(
    val id: String,
    val subjectId: String,
    val date: Long,
    val correctAnswers: Int,
    val totalQuestions: Int,
    val timeSpentInMinutes: Long
)