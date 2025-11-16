package com.shurdev.t_prep.domain.repositories

import com.shurdev.t_prep.domain.models.Card


interface CardRepository {
    suspend fun getCardByModule(moduleId: String): List<Card>
    suspend fun getCardById(id: String): Card?
    suspend fun toggleBookmark(cardId: String)
    suspend fun getBookmarkedCards(): List<Card>
}
