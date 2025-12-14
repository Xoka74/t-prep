package com.shurdev.t_prep.data.repositories

import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging
import com.shurdev.t_prep.data.api.AuthApi
import com.shurdev.t_prep.data.api.PushApi
import com.shurdev.t_prep.data.dataSource.AuthDataSource
import com.shurdev.t_prep.data.models.PushTokenDto
import com.shurdev.t_prep.domain.repositories.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val authApi: AuthApi,
    private val pushApi: PushApi,
) : AuthRepository {
    override val isAuthenticated = authDataSource.isAuthenticated

    override suspend fun setup() = authDataSource.setup()

    override suspend fun login(credential: GetCredentialResponse) {
        authenticate(credential)
        sendPushTokenToBackend()
    }

    private suspend fun authenticate(credential: GetCredentialResponse) {
        val idToken = getGoogleIdToken(credential)
        val authData = authApi.postLoginIdToken(idToken)
        authDataSource.save(authData)
    }

    private suspend fun sendPushTokenToBackend() {
        val token = Firebase.messaging.token.await()

        pushApi.pushDeviceToken(PushTokenDto(token))
    }

    private fun getGoogleIdToken(result: GetCredentialResponse): String {
        when (val credential = result.credential) {
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        val googleIdToken = GoogleIdTokenCredential.createFrom(credential.data)

                        return googleIdToken.idToken
                    } catch (_: GoogleIdTokenParsingException) {
                        throw NotImplementedError()
                    }
                } else {
                    throw NotImplementedError()
                }
            }

            else -> {
                throw NotImplementedError()
            }
        }
    }
}