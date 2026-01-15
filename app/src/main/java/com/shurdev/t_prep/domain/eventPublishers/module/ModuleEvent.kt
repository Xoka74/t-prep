package com.shurdev.t_prep.domain.eventPublishers.module

sealed interface ModuleEvent

class ModuleCreatedEvent : ModuleEvent

sealed class ModuleChangeEvent(open val moduleId: String) : ModuleEvent

data class ModuleEditedEvent(override val moduleId: String) : ModuleChangeEvent(moduleId)

data class ModuleCardEditedEvent(
    override val moduleId: String,
    val cardId: String,
) : ModuleChangeEvent(moduleId)

data class ModuleCardsAddedEvent(
    override val moduleId: String,
) : ModuleChangeEvent(moduleId)

data class ModuleCardDeletedEvent(
    override val moduleId: String,
    val cardId: String
) : ModuleChangeEvent(moduleId)

data class ModuleDeletedEvent(override val moduleId: String) : ModuleChangeEvent(moduleId)

class ModuleIntervalRepetitionEvent(moduleId: String) : ModuleChangeEvent(moduleId)