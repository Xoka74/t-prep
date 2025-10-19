package com.shurdev.t_prep.domain.models

data class Subject(
    val id: String,
    val name: String,
    val description: String,
    val totalQuestions: Int,
    val completedQuestions: Int
)