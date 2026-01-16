package com.shurdev.t_prep.data.repositories

import com.shurdev.t_prep.data.api.IntervalRepetitionsApi
import com.shurdev.t_prep.data.mappers.toDomainModel
import com.shurdev.t_prep.data.models.UpdateCardData
import com.shurdev.t_prep.domain.eventPublishers.module.ModuleEventPublisher
import com.shurdev.t_prep.domain.eventPublishers.module.ModuleIntervalRepetitionEvent
import com.shurdev.t_prep.domain.models.Card
import com.shurdev.t_prep.domain.repositories.IntervalRepetitionsRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class IntervalRepetitionsRepositoryImpl(
    private val intervalRepetitionsApi: IntervalRepetitionsApi,
    private val moduleEventPublisher: ModuleEventPublisher,
) : IntervalRepetitionsRepository {

    override suspend fun getCardsForRepetition(moduleId: String): List<Card> {
        return intervalRepetitionsApi.getCardsForRepetition(moduleId.toInt())
            ?.items
            ?.map { it.toDomainModel() }
            ?: emptyList()
    }

    /*override suspend fun getIsIntervalRepetitionsEnabled(moduleId: String): Boolean {
        return intervalRepetitionsApi.getIsIntervalRepetitionsEnabled(moduleId.toInt()) ?: false
    }*/

    override suspend fun setIsIntervalRepetitionsEnabled(moduleId: String, isEnabled: Boolean) {
        if (isEnabled) {
            intervalRepetitionsApi.enableIntervalRepetitions(moduleId.toInt())
        } else {
            intervalRepetitionsApi.disableIntervalRepetitions(moduleId.toInt())
        }

        moduleEventPublisher.push(ModuleIntervalRepetitionEvent(moduleId))
    }

    override suspend fun updateCardStatus(
        moduleId: String,
        cardId: String,
        answerDateTime: Date,
        isAnswerRight: Boolean
    ) {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        sdf.timeZone = TimeZone.getTimeZone("UTC")

        val formatted = sdf.format(Date())

        intervalRepetitionsApi.updateCardStatus(
            moduleId = moduleId.toInt(),
            cardId = cardId.toInt(),
            updateCardData = UpdateCardData(
                answerTime = formatted,
                rightAnswer = isAnswerRight
            )
        )

        moduleEventPublisher.push(ModuleIntervalRepetitionEvent(moduleId))
    }
}