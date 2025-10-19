package com.shurdev.t_prep.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
data class SubjectEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val totalQuestions: Int,
    val completedQuestions: Int = 0
)
