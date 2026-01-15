package com.shurdev.t_prep.domain.repositories

import com.darkrockstudios.libraries.mpfilepicker.MPFile
import com.shurdev.t_prep.data.models.CardData
import com.shurdev.t_prep.data.models.CardDataDto
import com.shurdev.t_prep.data.models.CardDto
import com.shurdev.t_prep.data.models.ModuleData
import com.shurdev.t_prep.domain.models.Card
import com.shurdev.t_prep.domain.models.Module


interface CardRepository {
    suspend fun getCardByModule(moduleId: String): List<Card>
    suspend fun createCard(data: CardDataDto): CardDto
    suspend fun getCardById(moduleId: Int, cardId: Int): Card?
    suspend fun importCards(file: MPFile<Any>): List<CardData>

    suspend fun editCard(
        moduleId: Int,
        cardId: Int,
        data: CardData
    )

    suspend fun deleteCard(moduleId: Int, cardId: Int)
}
