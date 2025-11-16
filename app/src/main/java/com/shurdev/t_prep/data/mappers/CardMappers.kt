package com.shurdev.t_prep.data.mappers

import com.shurdev.t_prep.data.models.CardDto
import com.shurdev.t_prep.domain.models.Difficulty
import com.shurdev.t_prep.domain.models.Card

fun CardDto.toDomainModel() : Card {
    return Card(
        id = id.toString(),
        moduleId = moduleId.toString(),
        correctAnswer = 1,
        question = question,
        options = listOf("1"),
        explanation = "Correct Answer",
        difficulty = Difficulty.MEDIUM
    )
}