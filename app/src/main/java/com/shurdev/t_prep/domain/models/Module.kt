package com.shurdev.t_prep.domain.models

data class Module(
    val id: String,
    val name: String,
    val description: String,
    val totalCards: Int,
    val completedCards: Int,
    val viewAccess: AccessLevel,
    val editAccess: AccessLevel,
)