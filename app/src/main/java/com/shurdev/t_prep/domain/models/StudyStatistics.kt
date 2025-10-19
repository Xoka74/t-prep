package com.shurdev.t_prep.domain.models

data class StudyStatistics(
    val totalSessions: Int,
    val totalTimeSpent: Long,
    val averageScore: Double
)