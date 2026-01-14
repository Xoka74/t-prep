package com.shurdev.t_prep.domain.repositories

interface IntervalRepetitionsRepository {
    suspend fun getCardsForRepetition(moduleId: String)

    suspend fun getIsIntervalRepetitionsEnabled(moduleId: String): Boolean

    suspend fun setIsIntervalRepetitionsEnabled(moduleId: String, isEnabled: Boolean)
}