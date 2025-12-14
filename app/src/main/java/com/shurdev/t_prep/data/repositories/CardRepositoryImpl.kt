package com.shurdev.t_prep.data.repositories

import com.shurdev.t_prep.data.api.CardsApi
import com.shurdev.t_prep.data.mappers.toDomainModel
import com.shurdev.t_prep.domain.models.Card
import com.shurdev.t_prep.domain.models.Difficulty
import com.shurdev.t_prep.domain.repositories.CardRepository

class CardRepositoryImpl(
    private val cardsApi: CardsApi,
) : CardRepository {
    override suspend fun getCardByModule(moduleId: String): List<Card> {
        /*return (0..2).map {
            Card(
                id=it.toString(),
                moduleId = "1",
                question = "Some question",
                options = listOf("1","2","3", "4"),
                correctAnswer = 1,
                explanation = "",
                difficulty = Difficulty.MEDIUM
            )
        }*/
        return cardsApi.getCardsByModuleId(moduleId.toInt()).map { it.toDomainModel() }
    }

    override suspend fun getCardById(id: String): Card? {
        return cardsApi.getCardById(id.toInt())?.toDomainModel()
    }
}