package com.shurdev.t_prep.data.repositories

import com.shurdev.t_prep.data.api.CardsApi
import com.shurdev.t_prep.data.mappers.toDomainModel
import com.shurdev.t_prep.domain.models.Card
import com.shurdev.t_prep.domain.repositories.CardRepository

class CardRepositoryImpl(
    private val cardsApi: CardsApi,
) : CardRepository {
    override suspend fun getCardByModule(moduleId: String): List<Card> {
        return cardsApi.getCardsByModuleId(moduleId.toInt()).map { it.toDomainModel() }
    }

    override suspend fun getCardById(id: String): Card? {
        return cardsApi.getCardById(id.toInt())?.toDomainModel()
    }

    override suspend fun toggleBookmark(cardId: String) {
    }

    override suspend fun getBookmarkedCards(): List<Card> {
        return emptyList()
    }
}