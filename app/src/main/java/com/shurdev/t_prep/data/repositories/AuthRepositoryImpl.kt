package com.shurdev.t_prep.data.repositories

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.shurdev.t_prep.data.api.AuthApi
import com.shurdev.t_prep.data.dataSource.AuthDataSource
import com.shurdev.t_prep.domain.repositories.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val context: Context,
    private val authApi: AuthApi,
    private val credentialManager: CredentialManager,
    private val serverClientId: String,
) : AuthRepository {
    override val isAuthenticated = authDataSource.isAuthenticated

    override suspend fun setup() = authDataSource.setup()

    override suspend fun login() {
        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(true)
            .setServerClientId(serverClientId)
            .setAutoSelectEnabled(true)
            .build()

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()


        val result = credentialManager.getCredential(
            request = request,
            context = context,
        )

        val idToken = getGoogleIdToken(result)
        val authData = authApi.postLoginIdToken(idToken)
        authDataSource.save(authData)
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