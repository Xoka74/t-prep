package com.shurdev.t_prep.domain.eventPublishers.module

import kotlinx.coroutines.flow.Flow

interface ModuleEventPublisher {
    val events: Flow<ModuleEvent>

    suspend fun push(event: ModuleEvent)
}