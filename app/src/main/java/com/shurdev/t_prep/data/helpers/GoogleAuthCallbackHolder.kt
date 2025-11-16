package com.shurdev.t_prep.data.helpers

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.coroutines.channels.Channel

class GoogleAuthCallbackHolder {
    var currentFire: ActivityResultLauncher<Intent>? = null
    var channel: Channel<Result<GoogleSignInAccount>> = Channel()

    fun updateCallback(activity: ComponentActivity) {
        currentFire = activity.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->

            val extras = result.data?.extras
            if (result.resultCode == RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

                task.addOnSuccessListener { value ->
                    channel.trySend(Result.success(value))
                }.addOnFailureListener { error ->
                    channel.trySend(Result.failure(error))
                }
            } else {
                channel.trySend(Result.failure(Exception()))
            }
        }
    }

    fun clearCallback() {
        currentFire = null
    }
}
