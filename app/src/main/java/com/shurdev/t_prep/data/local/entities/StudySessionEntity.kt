package com.shurdev.t_prep.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "study_sessions")
data class StudySessionEntity(
    @PrimaryKey
    val id: String,
    val moduleId: String,
    val date: Long,
    val correctAnswers: Int,
    val totalCards: Int,
    val timeSpentInMinutes: Long
)
