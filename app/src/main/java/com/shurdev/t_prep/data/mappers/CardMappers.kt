package com.shurdev.t_prep.data.mappers

import com.shurdev.t_prep.data.models.CardDto
import com.shurdev.t_prep.domain.models.Difficulty
import com.shurdev.t_prep.domain.models.Card

fun CardDto.toDomainModel() : Card {
    return Card(
        id = id,
        correctAnswer = rightAnswer,
        question = question,
        options = answerVariant,
    )
}