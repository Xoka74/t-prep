package com.shurdev.t_prep.domain.repositories

import com.shurdev.t_prep.data.models.CardDataDto
import com.shurdev.t_prep.data.models.CardDto
import com.shurdev.t_prep.domain.models.Card


interface CardRepository {
    suspend fun getCardByModule(moduleId: String): List<Card>
    suspend fun createCard(data: CardDataDto): CardDto
    suspend fun getCardById(id: String): Card?
    suspend fun toggleBookmark(cardId: String)
    suspend fun getBookmarkedCards(): List<Card>
}
