package com.shurdev.t_prep.data.publishers

import com.shurdev.t_prep.domain.eventPublishers.module.ModuleEvent
import com.shurdev.t_prep.domain.eventPublishers.module.ModuleEventPublisher
import kotlinx.coroutines.flow.MutableSharedFlow

class ModuleEventPublisherImpl : ModuleEventPublisher{
    override val events = MutableSharedFlow<ModuleEvent>(replay = 0)

    override suspend fun push(event: ModuleEvent) {
        events.emit(event)
    }
}