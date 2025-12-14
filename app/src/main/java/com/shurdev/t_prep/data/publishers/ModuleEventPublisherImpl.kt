package com.shurdev.t_prep.data.publishers

import com.shurdev.t_prep.domain.eventPublishers.module.ModuleEvent
import com.shurdev.t_prep.domain.eventPublishers.module.ModuleEventPublisher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow

class ModuleEventPublisherImpl : ModuleEventPublisher{
    private val channel = Channel<ModuleEvent>()


    override val events: Flow<ModuleEvent>
        get() = channel.consumeAsFlow()

    override suspend fun push(event: ModuleEvent) {
        channel.send(event)
    }
}