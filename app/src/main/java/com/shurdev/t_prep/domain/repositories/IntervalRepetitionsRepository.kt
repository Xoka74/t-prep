package com.shurdev.t_prep.domain.repositories

import com.shurdev.t_prep.domain.models.Card
import java.util.Date

interface IntervalRepetitionsRepository {
    suspend fun getCardsForRepetition(moduleId: String): List<Card>

//    suspend fun getIsIntervalRepetitionsEnabled(moduleId: String): Boolean

    suspend fun setIsIntervalRepetitionsEnabled(moduleId: String, isEnabled: Boolean)

    suspend fun updateCardStatus(moduleId: String, cardId: String, answerDateTime: Date, isAnswerRight: Boolean)
}