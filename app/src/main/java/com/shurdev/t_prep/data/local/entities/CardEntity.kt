package com.shurdev.t_prep.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class CardEntity(
    @PrimaryKey
    val id: String,
    val moduleId: String,
    val question: String,
//    val options: List<String>,
    val correctAnswer: Int,
    val explanation: String,
//    val difficulty: Difficulty,
    val isBookmarked: Boolean = false
)