package com.shurdev.t_prep.data.repositories

import com.shurdev.t_prep.data.api.IntervalRepetitionsApi
import com.shurdev.t_prep.domain.repositories.IntervalRepetitionsRepository

class IntervalRepetitionsRepositoryImpl(
    private val intervalRepetitionsApi: IntervalRepetitionsApi
) : IntervalRepetitionsRepository {

    override suspend fun getCardsForRepetition(moduleId: String) {
        TODO()
    }

    override suspend fun getIsIntervalRepetitionsEnabled(moduleId: String): Boolean {
        return intervalRepetitionsApi.getIsIntervalRepetitionsEnabled(moduleId.toInt()) ?: false
    }

    override suspend fun setIsIntervalRepetitionsEnabled(moduleId: String, isEnabled: Boolean) {
        if (isEnabled) {
            intervalRepetitionsApi.enableIntervalRepetitions(moduleId.toInt())
        } else {
            intervalRepetitionsApi.disableIntervalRepetitions(moduleId.toInt())
        }
    }
}