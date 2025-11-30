package com.shurdev.t_prep.data.repositories

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.shurdev.t_prep.data.api.AuthApi
import com.shurdev.t_prep.data.dataSource.AuthDataSource
import com.shurdev.t_prep.data.models.AuthData
import com.shurdev.t_prep.domain.repositories.AuthRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val context: Context,
    private val authApi: AuthApi,
    private val credentialManager: CredentialManager,
    private val serverClientId: String,
) : AuthRepository {
    @OptIn(DelicateCoroutinesApi::class)
    override val isAuthenticated = authDataSource.isAuthenticated

    override suspend fun setup(): Unit = authDataSource.setup()

    override suspend fun login() {
        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(true)
            .setServerClientId(serverClientId)
            .setAutoSelectEnabled(true)
            .build()

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        try {
            val result = credentialManager.getCredential(
                request = request,
                context = context,
            )

            val idToken = handleSignIn(result)


            val authData = authApi.postLoginIdToken(idToken)

            print("123")
            authDataSource.save(AuthData("test"))

        } catch (err: Exception) {
            print(err)
        }
    }

    private fun handleSignIn(result: GetCredentialResponse): String {
        when (val credential = result.credential) {
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        val googleIdTokenCredential =
                            GoogleIdTokenCredential.createFrom(credential.data)

                        return googleIdTokenCredential.idToken
                    } catch (e: GoogleIdTokenParsingException) {
//                        Log.e(TAG, "Received an invalid google id token response", e)
                        throw NotImplementedError()
                    }
                } else {
//                    // Catch any unrecognized custom credential type here.
//                    Log.e(TAG, "Unexpected type of credential")

                    throw NotImplementedError()
                }
            }

            else -> {
//                // Catch any unrecognized credential type here.
//                Log.e(TAG, "Unexpected type of credential")
                throw NotImplementedError()
            }
        }
    }
}