package com.shurdev.t_prep.domain.models

data class Card(
    val id: String,
    val question: String,
    val options: List<String>,
    val correctAnswer: Int,
)