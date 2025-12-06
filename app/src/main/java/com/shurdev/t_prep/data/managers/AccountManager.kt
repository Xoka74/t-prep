package com.shurdev.t_prep.data.managers

import androidx.activity.ComponentActivity
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption

class AccountManager(
    private val activity: ComponentActivity,
) {
    private val credentialManager = CredentialManager.create(activity)

    suspend fun login() : GetCredentialResponse {
        val serverClientId = ConfigManager.getConfig().serverClientId
        val googleIdOption: GetSignInWithGoogleOption = GetSignInWithGoogleOption.Builder(serverClientId)
//            .setFilterByAuthorizedAccounts(true)
//            .setServerClientId(serverClientId)
//            .setAutoSelectEnabled(true)
            .build()

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()


        return credentialManager.getCredential(
            request = request,
            context = activity,
        )
    }

    suspend fun logout() {
        credentialManager.clearCredentialState(ClearCredentialStateRequest())
    }
}