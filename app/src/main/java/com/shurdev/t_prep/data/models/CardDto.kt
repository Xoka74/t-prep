package com.shurdev.t_prep.data.models

data class CardDto(
    val id: Int,
    val question: String,
    val answer: String,
    val moduleId: Int,
    val createdAt: String,
    val updatedAt: String,
)