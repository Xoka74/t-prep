package com.shurdev.t_prep.domain.models

data class StudySession(
    val id: String,
    val moduleId: String,
    val date: Long,
    val correctAnswers: Int,
    val totalCards: Int,
    val timeSpentInMinutes: Long
)