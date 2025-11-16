package com.shurdev.t_prep.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "modules")
data class ModuleEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val totalCards: Int,
    val completedCards: Int = 0
)
