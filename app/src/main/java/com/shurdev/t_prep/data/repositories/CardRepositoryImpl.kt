package com.shurdev.t_prep.data.repositories

import android.app.Application
import androidx.core.net.toUri
import com.darkrockstudios.libraries.mpfilepicker.MPFile
import com.github.doyaaaaaken.kotlincsv.client.CsvReader
import com.shurdev.t_prep.data.api.CardsApi
import com.shurdev.t_prep.data.mappers.toDomainModel
import com.shurdev.t_prep.data.models.CardData
import com.shurdev.t_prep.data.models.CardDataDto
import com.shurdev.t_prep.data.models.CardDto
import com.shurdev.t_prep.domain.eventPublishers.module.ModuleCardDeletedEvent
import com.shurdev.t_prep.domain.eventPublishers.module.ModuleCardEditedEvent
import com.shurdev.t_prep.domain.eventPublishers.module.ModuleCardsAddedEvent
import com.shurdev.t_prep.domain.eventPublishers.module.ModuleEventPublisher
import com.shurdev.t_prep.domain.exceptions.FileReadException
import com.shurdev.t_prep.domain.exceptions.WrongStructureException
import com.shurdev.t_prep.domain.models.Card
import com.shurdev.t_prep.domain.repositories.CardRepository

class CardRepositoryImpl(
    private val cardsApi: CardsApi,
    private val application: Application,
    private val csvReader: CsvReader,
    private val moduleEventPublisher: ModuleEventPublisher,
) : CardRepository {
    override suspend fun getCardByModule(moduleId: String): List<Card> {
        return cardsApi.getCardsByModuleId(moduleId.toInt()).items.map { it.toDomainModel() }
    }

    override suspend fun createCard(data: CardDataDto): CardDto {
        return cardsApi.createCard(data.moduleId, CardData(data.question, data.answer))
    }

    override suspend fun createCards(moduleId: Int, data: List<CardDataDto>): List<CardDto> {
        val cards =  data.map {
            createCard(
                CardDataDto(
                    it.question,
                    it.answer,
                    it.moduleId
                )
            )
        }

        moduleEventPublisher.push(ModuleCardsAddedEvent(moduleId.toString()))

        return cards
    }

    override suspend fun getCardById(moduleId: Int, cardId: Int): CardData? {
        return cardsApi.getCardById(moduleId, cardId)
    }

    override suspend fun importCards(file: MPFile<Any>): List<CardData> {
        val inputStream = application.contentResolver.openInputStream(file.path.toUri())

        if (inputStream == null) {
            throw FileReadException()
        }

        try {
            val lines = csvReader.readAll(inputStream)

            return lines.map { line ->
                CardData(line[0], line[1])
            }
        } catch(_ : Exception) {
            throw WrongStructureException()
        } finally {
            inputStream.close()
        }
    }

    override suspend fun editCard(moduleId: Int, cardId: Int, data: CardData) {
        cardsApi.editCard(moduleId, cardId, data)

        moduleEventPublisher.push(ModuleCardEditedEvent(moduleId.toString(), cardId.toString()))
    }

    override suspend fun deleteCard(moduleId: Int, cardId: Int) {
        cardsApi.deleteCard(moduleId, cardId)

        moduleEventPublisher.push(ModuleCardDeletedEvent(moduleId.toString(), cardId.toString()))
    }
}