package com.shurdev.t_prep.services

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.shurdev.t_prep.data.api.PushApi
import com.shurdev.t_prep.data.dataSource.AuthDataSource
import com.shurdev.t_prep.data.models.PushTokenDto
import com.shurdev.t_prep.domain.eventPublishers.module.ModuleEventPublisher
import com.shurdev.t_prep.domain.eventPublishers.module.ModuleIntervalRepetitionEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class TPrepMessagingService : FirebaseMessagingService() {
    @Inject
    lateinit var pushApi: PushApi

    @Inject
    lateinit var authDataSource: AuthDataSource

    @Inject
    lateinit var moduleEventPublisher: ModuleEventPublisher

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        if (authDataSource.isAuthenticated.value != true) {
            return
        }

        runBlocking {
            pushApi.pushDeviceToken(PushTokenDto(token))
        }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        Log.i("TPrepMessagingService", "onMessageReceived")

        val moduleId = message.data["moduleId"] ?: return

        runBlocking {
            moduleEventPublisher.push(ModuleIntervalRepetitionEvent(moduleId))
        }
    }
}