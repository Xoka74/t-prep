package com.shurdev.t_prep.data.repositories

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.shurdev.t_prep.data.api.AuthApi
import com.shurdev.t_prep.data.dataSource.AuthDataSource
import com.shurdev.t_prep.data.helpers.GoogleAccountConsumer
import com.shurdev.t_prep.domain.repositories.AuthRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val context: Context,
    private val authApi: AuthApi,
    private val googleAccountConsumer: GoogleAccountConsumer,
    private val googleSignInClient: GoogleSignInClient,
    private val serverClientId: String,
) : AuthRepository {
    @OptIn(DelicateCoroutinesApi::class)
    override val isAuthenticated = authDataSource.isAuthenticated

    override suspend fun setup(): Unit = authDataSource.setup()

    override suspend fun login() {
        val authCode = googleAccountConsumer.authorize(googleSignInClient.signInIntent)

        // TODO: Parse server response and save it to AuthDataSource
        val response = authApi.postLoginCode(authCode)
    }
}