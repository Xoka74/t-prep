package com.shurdev.t_prep.data.repositories

import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.shurdev.t_prep.data.api.AuthApi
import com.shurdev.t_prep.data.dataSource.AuthDataSource
import com.shurdev.t_prep.domain.repositories.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val authApi: AuthApi,
) : AuthRepository {
    override val isAuthenticated = authDataSource.isAuthenticated

    override suspend fun setup() = authDataSource.setup()

    override suspend fun login(credential: GetCredentialResponse) {
        val idToken = getGoogleIdToken(credential)
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