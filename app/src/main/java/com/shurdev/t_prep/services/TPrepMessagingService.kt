package com.shurdev.t_prep.services

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class TPrepMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {

        Log.i("TOKEN", token)
        super.onNewToken(token)
    }
}